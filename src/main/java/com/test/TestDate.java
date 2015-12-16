package com.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {

	public static void main(String[] args) throws Exception{
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(new SimpleDateFormat("MMM dd yyyy").format(new Date()));
		Date today = formatter.parse(formatter.format(new Date()));

		Date endDate =formatter.parse("2015-09-16 00:00:00");
		if(endDate.after(today))	
			System.out.println("after");
		if(endDate.before(today))	
			System.out.println("before");
		if(endDate.compareTo(today)<0)	
			System.out.println("end date was in past, expired");
		System.out.println(today.getTime());
	}

}
