package com.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReplace {
	public static final String TRACKING_URL_PATTERN="\"(http://[a-zA-Z0-9]+\\.military\\.com/cgi-bin/outlog\\.cgi\\?url=(((?!\").)*))\"";
	public static final String TRACKING_PIXEL_URL_PATTERN="\"(http://[a-zA-Z0-9]+\\.military\\.com/cgi-bin/outlog\\.cgi\\?url=1(((?!\").)*))\"";
	
	public static void main(String[] args) throws Exception{
		String filePath="C:\\Users\\sfargose\\Workspaces\\cs\\test\\email.html";
		String outputFilePath="C:\\Users\\sfargose\\Workspaces\\cs\\test\\email1.html";
		String inputFileContent=readFile(filePath, Charset.forName("UTF-8"));

		String outputFileContent=trackingReplace(inputFileContent);
		writeFile(outputFilePath, outputFileContent);

		//System.out.println("beyond minus 12 active duty retiring what to expect".replaceAll("\\s+", "-"));

	}

	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}	

	public static String trackingReplace(String sBody) throws Exception{

		StringBuffer buf = new StringBuffer();
		//replace tracking pixel url with # 
		sBody=sBody.replaceAll(TRACKING_PIXEL_URL_PATTERN, "\"#\"");

		//remove tracking url wrapper and decode the url string
		Pattern urlPattern = Pattern.compile(TRACKING_URL_PATTERN);
		// Now create matcher object.
		Matcher urlMatcher = urlPattern.matcher(sBody);
		while (urlMatcher.find( )) {
			String href=urlMatcher.group(2);
			if(href!=null){
				href=href.replaceAll("&amp;", "&");
				if(href.indexOf("&")>0)
					href=href.substring(0,href.indexOf("&"));
			}
			String decodedUrl=URLDecoder.decode(href, "UTF-8");
			System.out.println(decodedUrl);
			String replaceString="\""+decodedUrl+"\"";
			urlMatcher.appendReplacement(buf, replaceString);
		}
		urlMatcher.appendTail(buf);
		
		
		/*sBody=buf.toString();
		
		
		//Add ESRC and code if not present in link
		buf = new StringBuffer();

		Pattern p = Pattern.compile("(<a\\s.*?['\\\"])(http://)(.*?)(['\\\"])");
		Matcher m = p.matcher(sBody);
		String sESRC = "Transition";
		// Process each match
		while (m.find()) {  
			System.out.println(m.group(3));
			if (!m.group(3).contains("ESRC")&& !m.group(3).contains("oasnx.monster.com")) { 

				String urlWithEsrc = m.group(3);

				// Add the ESRC on to the passed link
				if (urlWithEsrc.indexOf("?")==-1) {
					urlWithEsrc += "?ESRC=" + sESRC;
				} else {
					urlWithEsrc += "&ESRC=" + sESRC;
				}

				// Undo the encoding of the encoded token markers '{' 
				Pattern p3 = Pattern.compile("%7B%7B%7B");
				Matcher m3 = p3.matcher(urlWithEsrc);
				urlWithEsrc = m3.replaceAll("{{{");

				// Undo the encoding of the encoded token markers '}' 
				p3 = Pattern.compile("%7D%7D%7D");
				m3 = p3.matcher(urlWithEsrc);
				urlWithEsrc = m3.replaceAll("}}}");

				p3 = Pattern.compile("%2F");
				m3 = p3.matcher(urlWithEsrc);
				urlWithEsrc = m3.replaceAll("/");                

				// Put the url together
				urlWithEsrc = m.group(2)+ urlWithEsrc + "&code=" + sESRC + "&eml={{{TRACKING_EML}}}";

				m.appendReplacement(buf, m.group(1) + urlWithEsrc + m.group(4));
			}
		}

		m.appendTail(buf);   */

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
