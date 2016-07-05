package com.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;


public class Regex {
	public static void main(String[] args) throws Exception{
		
		//processNewscredPixel();
		//extractNewscredId("23.23");
		
		String str="A-10 'Thunderbolt' II \"The Gun\"";
		System.out.println(str.replaceAll("[\"']","\\\\$0"));
		
		System.out.println(StringEscapeUtils.escapeJava(str));
	}
	
	
	
	public static boolean extractNewscredId(String id){
		String guid=null;
		Pattern p = Pattern.compile("[-+]{0,1}[0-9]+(\\.[0-9]+){0,1}");
		Matcher m = p.matcher(id);

		 System.out.println(m.matches());
		return m.matches();
				
				
	}
	
	public static void processNewscredPixel()throws Exception{
		final String pixelRegex="<img[\\s]+src[\\s]*=[\\s]*[\"']http[s]*://pixel.newscred.com[\\s\\S]*/>";
		String test=FileUtil.readFile("test.txt");
		
		System.out.println(test.replaceAll(pixelRegex, ""));
	}
	
	public static void processKeywordRegex(){
		//final Pattern MILITARY_URL_PATTERN = Pattern.compile("^(http[s]?://)?[a-z\\.]*\\.military\\.com.*$");
				
		int keywordBoost =100;
		String keywordBoostFactor = "^"+keywordBoost;
		String[] keywords={"holiday", "thanksgiving day",
			"electronics"
		};
		String keywordQuery = "\"" + StringUtils.join(keywords, "\"" + keywordBoostFactor + " OR"+ " \"") + "\"" + keywordBoostFactor;
		System.out.println(keywordQuery);
		
		keywordQuery += " OR"+" \"" + StringUtils.join(keywords, "\""+ " OR"+" \"") + "\"";
		
		System.out.println(keywordQuery);
	}
	
	

}
