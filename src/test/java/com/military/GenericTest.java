package com.military;

import com.test.Generic;

import junit.framework.TestCase;

public class GenericTest extends TestCase {

	private Generic<String> testInstance;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		testInstance=new Generic<String>();
		assertNotNull(testInstance);
		
	}
	
	
	public void testAdd(){
		testInstance.add("Stany");
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		testInstance=null;
	}

}
