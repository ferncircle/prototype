package com.test.thread;

public class ThreadLocalExample {

	public static class MyRunnable implements Runnable {

		private ThreadLocal<Integer> threadLocalClassVar =
				new ThreadLocal<Integer>();
		private int nonThreadLocalClassVar=0;

		@Override
		public void run() {
			int random= (int) (Math.random() * 100D) ;
			threadLocalClassVar.set(random);
			nonThreadLocalClassVar=random;
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}

			System.out.println("threadlocal="+threadLocalClassVar.get()+
					" and nonThreadLocal="+nonThreadLocalClassVar+
					" and method variable="+random);
		}
	}


	public static void main(String[] args)throws Exception {
		MyRunnable sharedRunnableInstance = new MyRunnable();
		
		Thread thread1 = new Thread(sharedRunnableInstance);
		Thread thread2 = new Thread(sharedRunnableInstance);

		thread1.start();
		thread2.start();

		thread1.join(); //wait for thread 1 to terminate
		thread2.join(); //wait for thread 2 to terminate
	}

}
