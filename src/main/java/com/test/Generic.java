package com.test;

public class Generic<T> {

	private T t;

	public void add(T t) {
		this.t = t;
	}

	public T get() {
		return t;
	}

	// generic method printArray                         
	public static < E > void printArray( E[] inputArray )
	{
		// Display array elements              
		for ( E element : inputArray ){        
			System.out.printf( "%s ", element );
		}
		System.out.println();
	}

	public String testGet(){
		return null;
	}

	public static void main(String[] args) {
		Generic<Integer> integerBox = new Generic<Integer>();
		Generic<String> stringBox = new Generic<String>();

		integerBox.add(new Integer(10));
		stringBox.add(new String("Hello World"));

		System.out.printf("Integer Value :%d\n\n", integerBox.get());
		System.out.printf("String Value :%s\n", stringBox.get());
		
		
		// Create arrays of Integer, Double and Character
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
        Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

        System.out.println( "Array integerArray contains:" );
        printArray( intArray  ); // pass an Integer array

        System.out.println( "\nArray doubleArray contains:" );
        printArray( doubleArray ); // pass a Double array

        System.out.println( "\nArray characterArray contains:" );
        printArray( charArray ); // pass a Character array
	}
}
