package com.test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

public class Test {

	
	public static void main(String[] args) {
		Map<String,String> queryMap=new HashMap<String,String>();
		queryMap.put("keywords", "Electronics,Aircraft Electronics");
		
		final String QF_OR=" "+"OR"+" ";
		if(queryMap!=null && queryMap.keySet()!=null){
	    	
	    	for(String queryKey:queryMap.keySet()){
	    		StringBuffer qf=new StringBuffer();
	    		for(String item:queryMap.get(queryKey).split("[,;]")){
	    			
	    			if((item.split("\\s")).length>1)
	    				qf.append(queryKey+":"+"\""+item+"\""); //if multiword surround with double quotes
	    			else
	    				qf.append(queryKey+":"+item);
	    			
	    			qf.append(QF_OR);
	    		}
	    		qf.setLength(qf.length() - QF_OR.length());
	    		
	    	}    	
	    }
		
		String str="asfewa'a \n awf\"awfe";
		String out=str.replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n");
		
		

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
