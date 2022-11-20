/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

/** 
 * A class to implement a producer of things for a bounded buffer.  
 * It generates and puts items.
 * 
 * Taken from _Concepts in Programming Languages_ by John Mitchell
 * Comments by Scot Drysdale
 * @author John Mitchell
 */
public class Producer extends Thread {
	private final BoundedBuffer buffer;

	/**
	 * Create a Producer
	 * @param b the buffer the producer puts into
	 */
	public Producer(BoundedBuffer buffer) {
		this.buffer = buffer;
	}

	/**
	 * What to run when the thread is started 
	 */
	public void run() {
		try {
			for (int index = 0; index < 100; index ++) {
				buffer.put(index);
				sleep(100);
			}
		} catch (InterruptedException e) {	}
	}
}