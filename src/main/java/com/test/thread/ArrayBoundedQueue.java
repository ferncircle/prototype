/**
 * 
 */
package com.test.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/Condition.html
 *
 */
public class ArrayBoundedQueue {

	final private Lock lock=new ReentrantLock();
	final private Condition notFull=lock.newCondition();
	final private Condition notEmpty=lock.newCondition();

	final private Object[] items=new Object[100];
	int getPtr,putPtr, count;

	public void put(Object o){
		lock.lock();		
		try{			
			while(count==items.length)
				notFull.await();//wait till it's not full
			
			items[putPtr++]=o;
			count++;
			notEmpty.signal();
		}catch(InterruptedException e){}
		finally{
			lock.unlock();
		}

	}
	public Object get(){
		lock.lock();	
		Object o=null;
		try{			
			while(count==0)
				notEmpty.await();//wait till it's there is something
			count--;
			o=items[getPtr++ % items.length];
			notFull.signal();
		}catch(InterruptedException e){}
		finally{
			lock.unlock();
		}
		
		return o;

	}

	public static void main(String[] args) {
		

	}

}
