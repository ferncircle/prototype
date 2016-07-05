package com.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class PrintNumberThreadReentrant {

	public static void main(String[] args) {

		Integer oneI=new Integer(1);
		Integer twoI=new Integer(2);
		Integer threeI=new Integer(3);
		final Lock lock = new ReentrantLock();
		final Condition condition = lock.newCondition();

		ThreadInfo info=new ThreadInfo();
		info.setCurrentId(1);
		PrintNumber<Integer> one=new PrintNumber<Integer>(1, oneI, 2, info, lock,condition);
		PrintNumber<Integer> two=new PrintNumber<Integer>(2, twoI,3, info, lock,condition);
		PrintNumber<Integer> three=new PrintNumber<Integer>(3, threeI,1, info, lock,condition);
		three.start();
		two.start();
		one.start();


	}

	private static class ThreadInfo {

		private volatile int currentId;

		public int getCurrentId() {
			return currentId;
		}

		public void setCurrentId(int currentId) {
			this.currentId = currentId;
		}

	}

	private static class PrintNumber<T> extends Thread {

		int id;
		int nextId;
		T number;
		ThreadInfo info;

		Lock lock ;
		Condition condition;

		public PrintNumber(int id, T number, int nextId, ThreadInfo info, Lock lock, Condition condition) {
			super();
			this.id=id;
			this.number = number;
			this.nextId=nextId;
			this.info=info;
			this.lock=lock;
			this.condition=condition;
		}

		public  void doOperation(){
			while(true){
				lock.lock();
				try {
					while(id!=info.getCurrentId()){
						condition.await();

					}
					System.out.println(number);
					sleep(500);
					info.setCurrentId(nextId);
					condition.signalAll();

				} catch (InterruptedException e) {
					System.out.println(e);
				}finally{
					lock.unlock();
				}
			}


		}

		@Override
		public void run() {
			doOperation();

		}

	}



}
