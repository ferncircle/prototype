package com.test;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;


public class FileUtil {

	public static void main(String[] args) throws Exception{
		
		System.out.println(readFile("test.txt"));
	}
	
	public static String readFile(String file)throws Exception{
		return IOUtils.toString(new ClassPathResource(file).getInputStream());
	}

}
