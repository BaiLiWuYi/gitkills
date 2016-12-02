/**
 * 
 */
package com.infrastructure.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class TimeRoutine {
	
	static public class SysCurTime {
		private int year;
		private int month;
		private int day;
		private int week;
		private int hour;
		private int min;
		private int sec;
		
		public SysCurTime() {
			Calendar calendar = Calendar.getInstance();
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH)+ 1;
			day = calendar.get(Calendar.DAY_OF_MONTH);
			week = calendar.get(Calendar.DAY_OF_WEEK);
			hour = calendar.get(Calendar.HOUR_OF_DAY);
			min = calendar.get(Calendar.MINUTE);
			sec = calendar.get(Calendar.SECOND);			
		}

		public int getYear() {
			return year;
		}

		public int getMonth() {
			return month;
		}

		public int getDay() {
			return day;
		}

		/**
		 * 
		 * @return Calendar.MONDAY and so on.
		 * 	
		 */
		public int getWeek() {
			return week;
		}

		public int getHour() {
			return hour;
		}

		public int getMin() {
			return min;
		}

		public int getSec() {
			return sec;
		}
		
	}
	
	static public String takeTimeInFormat(String format, int year, 
			int month, int day, int hour, int min, int sec) {
		switch (format) {
		case "yyyy/MM/dd HH:mm:ss":
		case "yyyy-MM-dd HH:mm:ss":
			break;
		default:
			return null;
		}
				
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, min);
		calendar.set(Calendar.SECOND, sec);        
        
        SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date curDate = new Date(calendar.getTimeInMillis());		
		return formatter.format(curDate);        
	}
	
	static public String getSysTimeInFormat() {
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");//12Сʱ��
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//24Сʱ��
		Date curDate = new Date(System.currentTimeMillis());			
		return formatter.format(curDate);
	}		
	
	static public int getCurYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}
	
	static public int getCurMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH)+ 1;
	}
	
	static public int getCurDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	static public int getCurWeek() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	static public int getCurHour() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	static public int getCurMin() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MINUTE);
	}
	
	static public String putTimeInFormat(int hour, int min) {
		String strTime = new String();
		if (hour < 10) {
			strTime += "0"+ hour;
		} else {
			strTime = ""+ hour;
		}
		strTime += ":";
		if (min < 10) {
			strTime += "0"+ min;
		} else {
			strTime += ""+ min;
		}
		return strTime;
	}
	
	/**
	 * 
	 * @param time
	 * @return
	 *  the same as call: transTimeFromFormat2Long("yyyy/MM/dd HH:mm:ss", time);
	 */
	static public long transTimeInGenFormat2Long(String time) {
		return transTimeFromFormat2Long("yyyy/MM/dd HH:mm:ss", time);
	}
	
	/**
	 * 
	 * @param format
	 * @param time
	 * @return
	 *  -1, if fail.
	 */
	static public long transTimeFromFormat2Long(String format, String time) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date;
		try {
			date = formatter.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
		return date.getTime();
	}
	
}
