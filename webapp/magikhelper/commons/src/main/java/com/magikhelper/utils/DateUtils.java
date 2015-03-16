package com.magikhelper.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static Date addDaysToCurrentDate(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Date addDaysToDate(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Timestamp currentTimeStamp(Date date) {
		return new Timestamp(date.getTime());
	}

	public static Timestamp currentTimeStamp() {
		return currentTimeStamp(new Date());
	}

	public static java.util.Date convertToDate(String strDate, String dateFormat) {
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		java.util.Date date = null;
		try {
			date = df.parse(strDate);
		} catch (Exception e) {
			date = null;
		}
		return date;
	}

	public static String convertToString(java.util.Date date, String dateFormat) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(dateFormat);
			return df.format(date);
		}

		return null;
	}

}
