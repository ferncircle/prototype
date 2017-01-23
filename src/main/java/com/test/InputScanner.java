package com.test;

import java.util.Scanner;

public class InputScanner {
	
	public static void main(String[] str){
		Scanner sc =new Scanner(System.in);
		
		System.out.println("Please enter the command:");
		String command=sc.nextLine();
		System.out.println("You entered: "+command);
		
		
	}

}
