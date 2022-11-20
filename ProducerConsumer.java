
import java.util.Random;


/**
 *
 * @author Liang Zhong; T00224182
 */
public class ProducerConsumer {

    
    public static void main(String[] args) {
        /*create a bounded Queue with size 5 contains Integer type items.*/
        BoundedBuffer buffer = new BoundedBuffer(5);
        
        /*create a IntegerProducer object to add random integer numbers into the queue*/
        IntegerProducer producer = new IntegerProducer(buffer);
        
        /*create a Consumer object which will retrieve whatever type items in the queue*/
        Consumer consumer = new Consumer(buffer);
        
        /*starts both producer and consumer operations in their own threads*/
        producer.start();
        consumer.start();
        
    }
}


/*BoundedBuffer class object is a bounded queue which can contain integer numbers*/
class BoundedBuffer {
    private int buffer_size;   //Size of the bounded buffer queue
    
    private int[] buffer;  //buffer holding the circular queue
    
    private int count=0;      //number of elements in the queue
    private int in=0,out=0;   //location of the front and back of the queue
    
    /*consturctor to create a BoundedBuffer Queue object */
    public BoundedBuffer(int buffer_size)
    {
        if(buffer_size<5){
            throw new IllegalArgumentException("buffer size must >=5");
        }
        else{
        this.buffer_size=buffer_size;
        buffer=new int[this.buffer_size];
        }
    }
    
    /*Producer calls this method to add an item(integer number)  to the back of the queue
      Block insert operation, if the queue is full
      An ATOMIC OPERATION protected by a queue's mutex lock
    */
    public synchronized void insert(int item)
    {   /*while queue is full:1.this thread releases the mutex lock,2.put this thread in wait set*/
        while(count==this.buffer_size)
        {    
            try{wait();}     
            catch(InterruptedException e){e.printStackTrace();}
        }
        
        /*Or: queue is not full: insert an item in the queue at location 'in'*/
        buffer[in]=item;
        in = (in+1)%this.buffer_size;   //increment in to next position in the queue
        count++;   //insert an item increment count;remove an item decrement count
        System.out.println("Producer adds an item: "+item);
        notifyAll(); //wake up all threads in wait_set, so they can compete to run again
    }
    
    /*Remove an item from the front of the queue, an ATOMIC OPERATION protected by a queue's mutex lock
      Block remove operation, if the queue is full
    this method is called by the consumer object
    */
    public synchronized int remove() 
    {
        int item;
        
        /*while the queue is empty: this thread release the mutex lock and wait in wait set associated with this mutex lock */
        while(count==0)
        {
            try{wait();}
            catch(InterruptedException e){e.printStackTrace();}
        }
        
        /*Or: the queue is not empty: take an item from the queue and return it
              increment 'out' to the next positon in the queue and decrement 'count'
        */
        item=buffer[out];
        out=(out+1)%this.buffer_size;
        count--;
        System.out.println("Consumer just removed one item from the Queue: "+item);
        notifyAll();   //wake up all threads in the wait set, so they can compete to run again
        
        return item;
    }
}


/*IntegerProducer Class: an Producer object generates and insert items(integer numbers) into the queue
  it has its own thread running*/
class IntegerProducer extends Thread {
    private BoundedBuffer buffer;
    Random randomgenerator;
    
    /*Constructor: pass in a Queue to this Producter object for it to modify. */
    public IntegerProducer(BoundedBuffer buffer)
    {
        this.buffer=buffer;
        randomgenerator=new Random();
        
    }
    
    /*Override run() method for operations run in its own thread*/
    public void run()
    {
        try{
            while(true)  //Producer object continously create random integer numbers added into queue
            {
                int item=randomgenerator.nextInt(1000);
                buffer.insert(item);
                Thread.sleep(150);
            }
        }catch(Exception e){e.printStackTrace();}
    }
}

/*Comsumer Class: an Consumer object removes an item from the queue continuously,
  when the queue is empty, then put in wait set, until waked up after a producer adds an item.  */
class Consumer  extends Thread {
    private BoundedBuffer buffer;
    
    /*Constructor: pass in a Queue to this Consumer object for it to modify.*/
    public Consumer(BoundedBuffer buffer)
    {
        this.buffer=buffer;
    }
    
    /*Override run() method for operations run in its own thread*/
    @SuppressWarnings("unchecked")
    public void run()
    {
        try{
           while(true)  //consumer will remove items from the queue forever
           {
               int item;
               item=buffer.remove();   //consumer thread read each item from the queue
               Thread.sleep(200);  //put thread in sleep for 300 miliseconds
           }
        }
        catch(Exception e){e.printStackTrace();}
    }
}
