package com.test;


public class PrintNumberThread {

	public static void main(String[] args) {

		Integer oneI=new Integer(1);
		Integer twoI=new Integer(2);
		Integer threeI=new Integer(3);
		
		ThreadInfo info=new ThreadInfo();
		info.setCurrentId(1);
		PrintNumber<Integer> one=new PrintNumber<Integer>(1, oneI, 2, info);
		PrintNumber<Integer> two=new PrintNumber<Integer>(2, twoI,3, info);
		PrintNumber<Integer> three=new PrintNumber<Integer>(3, threeI,1, info);
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

		public PrintNumber(int id, T number, int nextId, ThreadInfo info) {
			super();
			this.id=id;
			this.number = number;
			this.nextId=nextId;
			this.info=info;
		}

		public  void doOperation(){
			while(true){
				synchronized (info) {
					try {
						while(id!=info.getCurrentId()){
							info.wait();

						}
						System.out.println(number);
						sleep(500);
						info.setCurrentId(nextId);
						info.notifyAll();

					} catch (InterruptedException e) {
						System.out.println(e);
					}
				}

			}
		}

		@Override
		public void run() {
			doOperation();

		}

	}
	


}
