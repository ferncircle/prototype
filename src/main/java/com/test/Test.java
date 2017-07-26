package com.test;

import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

public class Test {

	
	public static void main(String[] args)throws Exception {
		
		/*String str=ClasspathReplace.readFile("C:\\code\\svn\\stable\\military\\registration-widget\\"
				+ "widget\\web\\pages\\test\\doubleclick.html", Charset.forName("UTF-8"));
		System.out.println(URLDecoder.decode(str, "UTF-8"));*/
		
		System.out.println(URLDecoder.decode("%26","UTF-8"));

	}
	public static int[] removeArrayPart(int[] inputArray, int l, int r) {
	    int[] a=new int[inputArray.length-(r-l+1)];
	    for(int i=0, j=0;i<inputArray.length;i++){
	        if(i<l || i>r)
	            a[j++]=inputArray[i];
	    }
	    return a;
	}

}
