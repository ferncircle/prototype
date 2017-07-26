/**
 * 
 */
package com.test.thread;

/**
 * http://tutorials.jenkov.com/java-concurrency/locks.html#reentrance
 *
 */
public class ReentrantLock {

	int locksTaken=0;//we need count instead of boolean flag, since lock() can be called multiple times
	Thread lockedByThread;
	
	public synchronized void lock()throws InterruptedException{
		Thread curThread=Thread.currentThread();
		while(locksTaken>0 && curThread!=lockedByThread)
			wait();
		locksTaken++;
		lockedByThread=curThread;
	}
	
	public synchronized void unlock(){
		Thread curThread=Thread.currentThread();
		if(curThread==lockedByThread){
			if(locksTaken>0)
				locksTaken--;
			if(locksTaken==0)
				notifyAll();
		}
		
	}
	
}
