package com.test;

public class Finally {

	public static void main(String[] args) {
		
		System.out.println(testFinally());
	}
	
	public static int testFinally(){
		int a=12;
		
		try {
			a=34;
			Integer.parseInt("213s");
			//return a;
		} catch (NumberFormatException e) {
			System.out.println("in exception"+e.getMessage());
			return a;
		}finally{
			System.out.println("in finally block");
		}
		return a;
	}
}
