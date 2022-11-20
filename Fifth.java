/*Joining threads: the synchronous threading-the parent thread waits for all of its children threads to terminate before it returns*/

/*the main class*/
public class Fifth implements Runnable
{
     int id;     
     
     /*constructor*/
     Fifth(int id){
         this.id=id;
     }

     /*override the run() method from Runnable interface. it execute for the new thread:wait for 1 second and then print out its thred id*/
     public void run(){
         try { Thread.sleep(1000);}   //sleep 1000ms
         catch(InterruptedException ie) {}
         System.out.println("I am "+id+"'th worker thread.");
     }

     /*the main method*/
     public static void main(String args[]){
           Runnable worker = new Fifth(1);
           Thread runnerOne = new Thread(worker);

           Thread runnerTwo = new Thread(new Fifth(2));

           runnerOne.start();   //to create the new thread
           runnerTwo.start();

           try{runnerOne.join();}    //parent thread waits until the runnerOne thread terminates to resume
           catch(InterruptedException e) {}

           System.out.println("the first worker done.\n I am in the main thread again. ");
     }
}
