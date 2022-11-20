/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

import java.util.Random;
/** 
 * A class to implement a consumer of things from a bounded buffer.  
 * It gets and prints items.
 * 
 * Taken from _Concepts in Programming Languages_ by John Mitchell
 * Comments and modifications by Scot Drysdale
 * @author John Mitchell
 */
public class Consumer extends Thread {
	private final BoundedBuffer buffer;
	Random generator;

  /**
	 * Constructs a consumer of items from a bounded buffer
	 * @param b the bounded buffer the consumer takes items from
	 */
	public Consumer(BoundedBuffer buffer) {
		this.buffer = buffer;                  // The bounded buffer
		generator = new Random();    // Used to generate a random wait time
	}

	/**
	 * What to run when the thread is started 
	 */
	public void run() {
		try{
			while (true) {
				int n = buffer.get();
				int waitTime = 100 + generator.nextInt(200);
				sleep(waitTime);
				System.out.println(n);
			}
		} catch(InterruptedException e){}
	}
}
