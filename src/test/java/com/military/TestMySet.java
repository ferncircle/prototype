package com.military;

import com.test.MySet;

import junit.framework.TestCase;

public class TestMySet extends TestCase {

	private MySet<String> stringSet;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		stringSet=new MySet<String>();
	}
	
	public void testSet(){
		stringSet.add("Stany");
		assertEquals(stringSet.size(),1);
		
		stringSet.add("Steffi");
		assertEquals(stringSet.size(),2);
		assertTrue(stringSet.contains("Steffi"));
		
		assertFalse(stringSet.contains("Preeti"));
		stringSet.add("Preeti");
		assertEquals(stringSet.size(),3);
		assertTrue(stringSet.contains("Preeti"));	
		
		stringSet.remove("Preeti");
		assertFalse(stringSet.contains("Preeti"));
		assertEquals(stringSet.size(),2);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
