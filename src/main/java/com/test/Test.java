package com.test;

import java.util.HashMap;
import java.util.Map;

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
		

	}

}
