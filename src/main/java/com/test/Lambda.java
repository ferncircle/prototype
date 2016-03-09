package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.test.bean.Person;

public class Lambda {	

	interface Strategy {
		int operation(int a, int b);   
	}
	
	public int operateBinary(int a, int b, Strategy op) {
        return op.operation(a, b);
    }

	public static void main(String[] args) {/*
		Lambda lm=new Lambda();

		List<Person> folks=lm.createFolks();

		folks.stream().filter(p -> p.getName().startsWith("John")).map(p -> p.getName())
		.forEach(name -> System.out.println(name));

		//other example
		Strategy addition = (a, b) -> a + b; //annonymous method(interface method) definition
		System.out.println("40 + 2 = " +
	            lm.operateBinary(40, 2, addition)); //passing method(interface) implementation to other method
		
		Strategy subtraction = (a, b) -> {System.out.print(a+" - "+b+" = ");return (a - b);}; //annonymous method(interface method) definition
		System.out.println(lm.operateBinary(40, 2, subtraction)); //passing method(interface) implementation to other method


	*/}

	private List<Person> createFolks(){
		List<Person> folks=new ArrayList<Person>();
		Random r = new Random();
		for(int i=0;i<10;i++){
			Person p=new Person();
			p.setName("John"+r.nextInt(100-1) + 1);
			folks.add(p);

			p=new Person();
			p.setName("Smith"+r.nextInt(100-1) + 1);
			folks.add(p);
		}
		return folks;
	}

}
