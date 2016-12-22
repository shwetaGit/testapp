package com.app.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public final class DateShortCuts {

	/**
	 * @return Today date long value with start time set as 00:00:00
	 */
	public static long  todayStartTime(){
		return startTime(new Date());
	}

	/**
	 * @return Today date long value with end time set as 23:59:59
	 */

	public static long  todayEndTime(){
		return endTime(new Date());
	}

	/**
	 * @return Yesterday date.
	 */

	public static Date yesterday(final int day){
		final Date d = new Date();
		d.setDate(d.getDate() - day);
		return d;
	}

	/**
	 * @return Yesterday date long value with start time set as 00:00:00
	 */

	public static long  yesterdayStartTime(){
		return startTime(yesterday(1));
	}

	/**
	 * @return Yesterday date long value with end time set as 23:59:59
	 */
	public static long   yesterdayEndTime(){
		return endTime(yesterday(1));
	}

	/**
	 * @return Date before Yesterday date long value with start time set as 00:00:00
	 */

	public static long  dayB4YesterdayStartTime(){
		return startTime(yesterday(2));
	}

	/**
	 * @return Date before Yesterday date long value with end time set as 23:59:59
	 */
	public static long   dayB4YesterdayEndTime(){
		return endTime(yesterday(2));
	}

	/**
	 * @return Last week First day long value with start time set as 00:00:00
	 */
	public static long  lastWeekFirstDate(){
		final java.util.Date date =  new Date(currentWeekFirstDate());
		date.setDate(date.getDate()-7);
		return date.getTime();

	}

	/**
	 * @return Last week Last day long value with end time set as 23:59:59
	 */
	public static long  lastWeekEndDate(){
		final java.util.Date date =  new Date(currentWeekLastDate());
		date.setDate(date.getDate()-7);
		return date.getTime();

	}

	/**
	 * @return Current week First day long value with start time set as 00:00:00
	 */
	public static long  currentWeekFirstDate() {
		final Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_YEAR);
		while(cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
			cal.set(Calendar.DAY_OF_YEAR, --day);
		}
		return startTime(cal.getTime());
	}

	/**
	 * @return Current week last day long value with end time set as 23:59:59
	 */
	public static long  currentWeekLastDate() {
		final Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_YEAR);
		while(cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY){
			cal.set(Calendar.DAY_OF_YEAR, ++day);
		}
		return endTime(cal.getTime());
	}


	/**
	 * @return Current month first day long value with start time set as 00:00:00
	 */
	public static long  currentMonthFirstDate(){
		final java.util.Date date =  new Date();
		date.setDate(1);
		return startTime(date);
	}

	/**
	 * @return Current month last day long value with end time set as 23:59:59
	 */
	public static long  currentMonthLastDate(){
		final java.util.Date date =  new Date();
		date.setDate(Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
		return endTime(date);
	}

	/**
	 * @return Last month first day long value with start time set as 00:00:00
	 */
	public static long  lastMonthFirstDate(){
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		return startTime(cal.getTime());
	}


	/**
	 * @return Last 3 month first day long value with start time set as 00:00:00
	 */
	public static long  last3MonthFirstDate(){
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -90);
		return startTime(cal.getTime());
	}

	/**
	 * @return Last 6 month last day long value with end time set as 23:59:59
	 */
	public static long  last6MonthFirstDate(){
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -180);
		return startTime(cal.getTime());
	}

	/**
	 * @return Current year first day long value with start time set as 00:00:00
	 */
	public static long  currentYearFirstDate(){
		final Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), 0, 1);
		return startTime(cal.getTime());
	}

	/**
	 * @return Current year last day long value with start time set as 23:59:59
	 */
	public static long  currentYearLastDate(){
		final Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), 11, 31);
		return endTime(cal.getTime());
	}

	/**
	 * @return Last year first day long value with start time set as 00:00:00
	 */
	public static long  lastYearFirstDate(){
		final Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR)-1, 0, 1);
		return startTime(cal.getTime());
	}

	/**
	 * @return Last year last day long value with start time set as 23:59:59
	 */
	public static long  lastYearLastDate(){
		final Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR)-1, 11, 31);
		return startTime(cal.getTime());
	}

	/**
	 * @return date long value with start time set as 00:00:00
	 */
	public static long  startTime(final java.util.Date date) {
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		return date.getTime();
	}

	/**
	 * @return date long value with start time set as 23:59:59
	 */
	public static long  endTime(final java.util.Date date) {
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		return date.getTime();
	}

	public static long convertToUTC(final String userDate, final String timeZoneLabel) throws  java.text.ParseException{
		final SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy HH:mm:ss z");
		format.setTimeZone(TimeZone.getTimeZone(timeZoneLabel));
		final Date parsedDate=new Date(format.parse(userDate).getTime());
		return parsedDate.getTime();
	}

	public static void main(final String[] args) throws ParseException 
	{
	/*	TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		//System.out.println("LAST_3_MONTH :  \tfrom date ["+new Date(DateShortCuts.last3MonthFirstDate())+"] to date ["+new Date(DateShortCuts.lastMonthLastDate())+"]");

		System.out.println("TODAY : \t \tfrom date ["+new Date(DateShortCuts.todayStartTime())+"] to date ["+new Date(DateShortCuts.todayEndTime())+"]");
		System.out.println("YESTERDAY :  \t \tfrom date ["+new Date(DateShortCuts.yesterdayStartTime())+"] to date ["+new Date(DateShortCuts.yesterdayEndTime())+"]");
		System.out.println("DAY_B4_YESTERDAY :  \tfrom date ["+new Date(DateShortCuts.dayB4YesterdayStartTime())+"] to date ["+new Date(DateShortCuts.dayB4YesterdayEndTime())+"]");
		System.out.println("THIS_WEEK :  \t \tfrom date ["+new Date(DateShortCuts.currentWeekFirstDate())+"] to date ["+new Date(DateShortCuts.currentWeekLastDate())+"]");
		System.out.println("LAST_WEEK :  \t \tfrom date ["+new Date(DateShortCuts.lastWeekFirstDate())+"] to date ["+new Date(DateShortCuts.lastWeekEndDate())+"]");
		System.out.println("THIS_MONTH :  \t \tfrom date ["+new Date(DateShortCuts.currentMonthFirstDate())+"] to date ["+new Date(DateShortCuts.currentMonthLastDate())+"]");
		System.out.println("LAST_MONTH :  \t \tfrom date ["+new Date(DateShortCuts.lastMonthFirstDate())+"] to date ["+new Date(DateShortCuts.todayEndTime())+"]");
		System.out.println("LAST_3_MONTH :  \tfrom date ["+new Date(DateShortCuts.last3MonthFirstDate())+"] to date ["+new Date(DateShortCuts.todayEndTime())+"]");
		System.out.println("LAST_6_MONTH :  \tfrom date ["+new Date(DateShortCuts.last6MonthFirstDate())+"] to date ["+new Date(DateShortCuts.todayEndTime())+"]");
		System.out.println("THIS_YEAR :  \t \tfrom date ["+new Date(DateShortCuts.currentYearFirstDate())+"] to date ["+new Date(DateShortCuts.currentYearLastDate())+"]");
		System.out.println("LAST_YEAR :  \t \tfrom date ["+new Date(DateShortCuts.lastYearFirstDate())+"] to date ["+new Date(DateShortCuts.lastYearLastDate())+"]");
*/

	}
}
