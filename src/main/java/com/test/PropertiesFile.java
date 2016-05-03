package com.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class PropertiesFile {

	public static void main(String[] args) {
		Map<String, String> map1=convertPropertiesToMap(loadPropertiesFile("C:\\Github\\milcom\\temp\\sfpsvc01_service-urls.properties"));
		Map<String, String> map2=convertPropertiesToMap(loadPropertiesFile("C:\\Github\\milcom\\temp\\sfdtool02_service-urls.properties"));

		compare(map1, map2);
	}
	
	public static Map<String, String> convertPropertiesToMap(Properties prop){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (String key : prop.stringPropertyNames()) {
			map.put(key, prop.getProperty(key));
		}
		return map;
	}
	
	public static void compare(Map<String, String> map1, Map<String, String> map2){
		System.out.println(map1.size());
		System.out.println(map2.size());
		
		Iterator<Entry<String, String>>  entries = map1.entrySet().iterator();
		while (entries.hasNext()) {
		  Entry<String, String> thisEntry = (Entry<String, String>) entries.next();
		  Object key = thisEntry.getKey();
		  //Object value = thisEntry.getValue();
		  if(map2.containsKey(key)){
			  map2.remove(key);
			  entries.remove();
		  }
		  
		}
		
		System.out.println("Map1 Remaining Keys:");
		printMap(map1);
		
		System.out.println("Map2 Remaining Keys:");
		printMap(map2);
	}
	
	public static void printMap(Map<String, String> map){
		for (Map.Entry<String, String> entry : map.entrySet())
		{
		    System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}
	
	public static Properties loadPropertiesFile(String filePath){
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(filePath);

			// load a properties file
			prop.load(input);


		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return prop;

	  }
	

}
