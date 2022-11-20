
package producerconsumer;

/**
 *producer and consumer problem
 * @author Kevin
 */
public class ProducerConsumer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer(5); // buffer has size 5
		Producer prod = new Producer(buffer);
		Consumer cons1 = new Consumer(buffer);
		Consumer cons2 = new Consumer(buffer);
		prod.start();
		cons1.start();
		cons2.start();
		try {
			prod.join();
			cons1.interrupt();
			cons2.interrupt();
		} catch (InterruptedException e) {
		}
		System.out.println("End of Program");
        
    }
    
}
