package com.test;

import org.apache.commons.lang3.StringUtils;


public class Regex {
	public static void main(String[] args) throws Exception{
		
		processNewscredPixel();
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
