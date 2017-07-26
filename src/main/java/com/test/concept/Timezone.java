/**
 * 
 */
package com.test.concept;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 
 *
 */
public class Timezone {

	public static String getServerTimezone()throws Exception{

		Calendar calGmt = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		Calendar calPst = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
		long milliDiff = calPst.get(Calendar.ZONE_OFFSET);
		/*System.out.println("PST TIME="+calPst.getTime().getTime());
		System.out.println("GMT time="+calGmt.getTime().getTime());
		System.out.println("PST offset="+TimeZone.getTimeZone("America/Los_Angeles").getRawOffset());
		*/
		String createDate="2017-01-26";
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = (Date)formatter.parse(createDate);  
		// Got local offset, now loop through available timezone id(s).
		String [] ids = TimeZone.getAvailableIDs();
		String name = null;
		for (String id : ids) {
		  TimeZone tz = TimeZone.getTimeZone(id);
		  if (tz.getRawOffset() == milliDiff) {
		    // Found a match.
		    name = id;
		    break;
		  }
		}

		
		System.out.println(name);
		return name;
	}
	
	public static void test(){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(1439473159063l);
		
		System.out.println(cal.getTime());
	}
	
	
	public static void main(String[] args)throws Exception {
		
		//Timezone.getServerTimezone();
		Timezone.test();
	}

}
