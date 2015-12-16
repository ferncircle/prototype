package com.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClasspathReplace {
	public static final String DEFAULT_CLASSPATH_FILE="C:\\Github\\milcom\\unitpages\\.classpath";
	public static final String CLASSPATH_PATTERN="(<classpathentry .*kind=\"lib\".*)(/>)";
	public static final String CLASSPATH_REPLACEMENT=">\n"+
		"		<attributes>\n"+
		"			<attribute name=\"org.eclipse.jst.component.dependency\" value=\"/WEB-INF/lib\"/>\n"+
		"		</attributes>\n"+
		"	</classpathentry>";
	
	
	public static void main(String[] args) throws Exception{
		Scanner reader = new Scanner(System.in);
		System.out.println("classpath file path:");
		String filePath = reader.nextLine();
		reader.close();
		if(filePath==null || filePath.trim().equals(""))
			filePath=DEFAULT_CLASSPATH_FILE;
		//String filePath="C:\\Users\\sfargose\\Workspaces\\cs\\test\\email.html";
		String outputFilePath=filePath;
		
		String inputFileContent=readFile(filePath, Charset.forName("UTF-8"));

		String outputFileContent=classPathReplace(inputFileContent);
		writeFile(outputFilePath, outputFileContent);

		//System.out.println("beyond minus 12 active duty retiring what to expect".replaceAll("\\s+", "-"));
		
	}

	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	public static String classPathReplace(String sBody) throws Exception{		
		StringBuffer buf = new StringBuffer();
		
		Pattern urlPattern = Pattern.compile(CLASSPATH_PATTERN);
		// Now create matcher object.
		Matcher urlMatcher = urlPattern.matcher(sBody);
		while (urlMatcher.find( )) {
			String replaceString=urlMatcher.group(1)+CLASSPATH_REPLACEMENT;
			System.out.println(replaceString);
			urlMatcher.appendReplacement(buf, replaceString);
		}
		urlMatcher.appendTail(buf);
		
		return buf.toString();
	}

	public static void writeFile(String path, String data){
		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter( new FileWriter(path));
			writer.write(data);
		}
		catch ( IOException e)
		{
		}
		finally
		{
			try
			{
				if ( writer != null)
					writer.close( );
			}
			catch ( IOException e)
			{
			}
		}
	}

}
