/**
 * 
 */
package com.test.thread;

/**
 * http://tutorials.jenkov.com/java-concurrency/read-write-locks.html
 *
 */
public class ReadWriteLock {
	
	private int readers=0;
	private int writers=0;
	private int writeRequests=0;
	
	public synchronized void readLock() throws InterruptedException{
		if(writers>0 || writeRequests>0)
			wait();
		readers++;
	}
	public synchronized void releaseReadLock() {
		readers--;
		notifyAll();
	}
	
	public synchronized void writeLock()throws InterruptedException{
		writeRequests++;
		if(writers>0 || readers>=0){
			wait();
		}
		writeRequests--;
		writers++;
	}
	
	public synchronized void releaseWriteLock(){
		writers--;
		notifyAll();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
