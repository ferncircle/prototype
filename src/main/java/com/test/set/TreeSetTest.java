/**
 * 
 */
package com.test.set;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author SFargose
 *
 */
public class TreeSetTest {

	TreeSet<Integer> set=new TreeSet<Integer>();
	
	public boolean twoSum(int value){
		boolean contains=false;
		int forwardPtr=0;
		int reversePtr=set.size()-1;
		
		Iterator<Integer> forward=set.iterator();
		Iterator<Integer> reverse=set.descendingIterator();
		int i=forward.next();
		int j=reverse.next();
		while(forwardPtr<reversePtr){
			int sum=i+j;
			if(sum==value) 
				return true;
			else if(sum<value){
				i=forward.next();
				forwardPtr++;
			}else{
				j=reverse.next();
				reversePtr--;
			}		
		}
		
		return contains;
	}
	
	public void add(int item){
		set.add(item);
	}
	public static void main(String[] args) {
		
		TreeSetTest test=new TreeSetTest();
		test.add(4);test.add(5);test.add(6);test.add(8);test.add(12);
		
		System.out.println(test.twoSum(9));

	}

}
