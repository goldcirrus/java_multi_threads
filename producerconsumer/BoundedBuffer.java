/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

/**
 *
 * @author Kevin
 * @param <E>
 */
public class BoundedBuffer {
   protected int numSlots; 
   private int[] buffer;
   private int takeOut = 0, putIn = 0; 
   private int count=0;

   /*consturctor to create a buffer*/
   public BoundedBuffer(int numSlots) {    
      if(numSlots <= 0) {
         throw new IllegalArgumentException( "numSlots <= 0");
      } 
      this.numSlots = numSlots; 
      buffer = new int[numSlots];
   }
 
 	/**
 	 * Put an item in the bounded buffer.  Block if full.
 	 * @param value the thing to add to the rear of the buffer
 	 * @throws InterruptedException
 	 */
   public synchronized void put(int value) throws InterruptedException {
      while (count == numSlots) 
      	wait();
      
      buffer[putIn] = value;
      putIn = (putIn + 1) % numSlots;
      count++;
      notifyAll();
   }

 	/** 
 	 * Remove an item from a bounded buffer.  Block if empty
 	 * @return the item removed
 	 * @throws InterruptedException
 	 */
   public synchronized int get() throws InterruptedException {
      while (count == 0) 
      	wait();
      
      int value = buffer[takeOut];
      takeOut = (takeOut + 1) % numSlots;
      count--;
      notifyAll();
      return value;
   }
    
}
