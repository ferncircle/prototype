package com.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;


public class Regex {
	public static void main(String[] args) throws Exception{

		//processNewscredPixel();
		//extractNewscredId("23.23");

		String str="chat\"><img src=\"n\" onload=\"alert(document.cookie)\">_discussion?";
		//System.out.println(str.replaceAll("[\"']","\\\\$0"));

		//System.out.println(StringEscapeUtils.escapeJava(str));
		
		System.out.println(str.replaceAll("[<>()\"\\?=]", ""));
		String pattern="/hero(/.*)?$";
		String replace="/veteran-jobs$1";
		assertThat("www.military.com/he2ro".replaceAll(pattern, replace),
				is("www.military.com/he2ro"));
		assertThat("www.military.com/hero".replaceAll(pattern, replace),
				is("www.military.com/veteran-jobs"));
		assertThat("www.military.com/hero/sef".replaceAll(pattern, replace),
				is("www.military.com/veteran-jobs/sef"));
		assertThat("www.military.com/herosef".replaceAll(pattern, replace),
				is("www.military.com/herosef"));
		
		pattern="/entertainment(?!/outdoor-guide)(/.*)?$";
		replace="/off-duty$1";
		assertThat("www.military.com/entertainment".replaceAll(pattern, replace),
				is("www.military.com/off-duty"));
		assertThat("www.military.com/entertainment/abc".replaceAll(pattern, replace),
				is("www.military.com/off-duty/abc"));
		assertThat("www.military.com/entertainment/outdoor-guide".replaceAll(pattern, replace),
				is("www.military.com/entertainment/outdoor-guide"));
		assertThat("www.military.com/entertainment/sef".replaceAll(pattern, replace),
				is("www.military.com/off-duty/sef"));
		assertThat("www.military.com/entertainmentsef".replaceAll(pattern, replace),
				is("www.military.com/entertainmentsef"));
		
		
		System.out.println("All test cases passed");
	}



	public static boolean extractNewscredId(String id){
		String guid=null;
		Pattern p = Pattern.compile("[-+]{0,1}[0-9]+(\\.[0-9]+){0,1}");
		Matcher m = p.matcher(id);

		System.out.println(m.matches());
		return m.matches();


	}

	public static void testRegex(){
		String regex="/hero(/.*)";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher("www.military.com/hero");

		System.out.println(m.replaceAll("/veteran-jobs$0"));
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
