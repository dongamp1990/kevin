package org.kevin.util;
/**
 *=====================================================================
 * ACP Date Type Data Handling Utility
 *
 *
 *---------------------------------------------------------------------
 * Update date  Contents
 *=====================================================================
 * 12/10/2012   create
 *
 */

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {
    private static Logger mLogger = LoggerFactory.getLogger(DateUtil.class.getName());
    /**
     * yyyy-MM-dd HH:mm:ss"
     */
	public static SimpleDateFormat sdf_bidStatus = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	public static SimpleDateFormat date_sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat english_sdf = new SimpleDateFormat("MMMM dd yyyy");
	public static SimpleDateFormat sdf_min = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static SimpleDateFormat sdf_date = new SimpleDateFormat("MM-dd HH:mm");
	public static SimpleDateFormat sdf_min_12 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	public static SimpleDateFormat sdf_china = new SimpleDateFormat("yyyy年MM月dd日");
	
	public static SimpleDateFormat sdf_china_mm_dd = new SimpleDateFormat("MM月dd日");

	public static SimpleDateFormat sdf_min_am_pm = new SimpleDateFormat("a");

	public static SimpleDateFormat news_sdf = new SimpleDateFormat("yyyy-MM-dd hh:mmaa");

	public static SimpleDateFormat date_sdf2 = new SimpleDateFormat("yyyy/MM/dd");

	public static SimpleDateFormat date_sdf_statics = new SimpleDateFormat("yyyyMMdd");

	public static SimpleDateFormat month_sdf_statics = new SimpleDateFormat("yyyyMM");
	public static SimpleDateFormat month_day_sdf_statics = new SimpleDateFormat("MM-dd");
	public static SimpleDateFormat year_sdf_statics = new SimpleDateFormat("yyyy");
	public static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

    public static SimpleDateFormat memberday_defaultTime = new SimpleDateFormat("yy/MM/dd");
    public static SimpleDateFormat memberday_finalTime = new SimpleDateFormat("MM/dd");

    public static String BIRTHDAYREGEX = "(19|20)\\d{2}(1[0-2]|0?[1-9])(0?[1-9]|[1-2][0-9]|3[0-1])";

	public static SimpleDateFormat CRONEXPRESSION_FORMAT = new SimpleDateFormat("ss mm HH dd MM ? yyyy");

	public static Pattern BIRTHDAYPATTERN = Pattern.compile(BIRTHDAYREGEX);

	/**
	 * Convert date to GMT
	 *
	 * @param date
	 *            source date
	 * @return Date GMT Date
	 */
	public static Date convertToGmt(Date date) {
		TimeZone tz = TimeZone.getDefault();

		Date retDate = null;
		if ( date != null ) {
			retDate = new Date(date.getTime() - tz.getRawOffset());

			// if we are now in DST, back off by the delta. Note that we are
		    // checking the GMT date, this is the KEY.
		    if (tz.inDaylightTime(retDate)) {
			   Date dstDate = new Date(retDate.getTime() - tz.getDSTSavings());

			   // check to make sure we have not crossed back into standard time
			   // this happens when we are on the cusp of DST (7pm the day before
			   // the change for PDT)
			  if (tz.inDaylightTime(dstDate)) {
				 retDate = dstDate;
			  }
		    }
		}
		return retDate;
	}

	/**
	 * Parse date to string according to specific format
	 *
	 * @param date
	 * @param sdf
	 * @return
	 */
	public static String parseDateToString(Date date, SimpleDateFormat sdf) {
		if (date == null || sdf == null) {
			return null;
		} else {
			return sdf.format(date);
		}
	}

	/**
	 * Parse date to string according to specific format
	 *
	 * @param date
	 * @param sdf
	 * @return
	 */
	public static String parseDateToString(Date date, SimpleDateFormat sdf, String timezoneStr) {
		if (date == null || sdf == null) {
			return null;
		} else {
			TimeZone timezone = TimeZone.getTimeZone(timezoneStr);
			sdf.setTimeZone(timezone);
			return sdf.format(date).toLowerCase();
		}
	}

	/**
	 * Parse a string specific format with to date
	 *
	 * @param dateStr
	 * @param sdf
	 * @return
	 */
	public static Date parseStringToDate(String dateStr, SimpleDateFormat sdf)  {
		if (dateStr == null || sdf == null) {
			return null;
		} else {
			try {
				return sdf.parse(dateStr);
			} catch (ParseException e) {
				String errMsg = "Can't find parse the '" + dateStr + "' to date' with format" + sdf.toPattern()	+ ".";
				mLogger.error(errMsg);
//			    throw new UtilityException(ExceptionType.EXCEPTION_UTILITY,
//					                   ErrorVOGenerator.genarateErrorVO("", "",  "", errMsg));
				return null;
			}
		}
	}


	/**
	 * Difference in days for current.
	 *
	 * @param countyAuctionDT the county auction dt
	 * @return the int
	 */
	public static int differenceInDaysForCurrent(Date countyAuctionDT)  {
		return  (int) ( ((Calendar.getInstance().getTime()).getTime() - countyAuctionDT.getTime()) / (1000 * 60 * 60 * 24) );
	}

	/**
	 * Difference in minutes for current.
	 *
	 * @param countyAuctionDT the county auction dt
	 * @return the int
	 */
	public static int differenceInMinutesForCurrent(Date countyAuctionDT)  {
		return  (int) ( ((Calendar.getInstance().getTime()).getTime() - countyAuctionDT.getTime()) / (1000 * 60) );
	}

	/**
	 * Difference in seconds for current.
	 *
	 * @param aDate
	 * @return int
	 */
	public static int differenceInSecondsForCurrent(Date aDate)  {
		return  (int) ( ((Calendar.getInstance().getTime()).getTime() - aDate.getTime()) / 1000 );
	}

	public static Date convertTimeByTimezone(Date date, String timezoneStr){
		TimeZone timezone = TimeZone.getTimeZone(timezoneStr);
		Calendar calendar = new GregorianCalendar(timezone);
        calendar.setTime(new Date());
        return calendar.getTime();
	}

    public static String convertDateToString(Date startDate, String timezoneStr) {
        String newDateString = "";
        TimeZone timezone = TimeZone.getTimeZone(timezoneStr);
		Calendar cal = new GregorianCalendar(timezone);
		cal.setTime(startDate);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		String weekString = new DateFormatSymbols().getWeekdays()[week];
		int month = cal.get(Calendar.MONTH);
		String monthString = new DateFormatSymbols().getMonths()[month];
		int date = cal.get(Calendar.DATE);
		int year = cal.get(Calendar.YEAR);
		newDateString = weekString + "  |  " + monthString + " " + date + ", " + year;
        return newDateString;
    }

    public static String getDateDisplayForComment(Date date){
    	if(date==null){
    		return null;
    	}
		String displayeTime = "";
		int min = DateUtil.differenceInMinutesForCurrent(date);
		if(min<60){
			if(min<1){
				displayeTime = "just now";
			}else{
				displayeTime = min+" minutes ago";
			}
		}else if (min<60*24){
			displayeTime = min/60 +" hours ago";
		}else{
			displayeTime = DateUtil.parseDateToString(date, DateUtil.date_sdf);
		}
		return displayeTime;
    }

    public static String getDateDisplayForNews(Date date){
    	if(date==null){
    		return null;
    	}
		String displayeTime = "";
		int min = DateUtil.differenceInMinutesForCurrent(date);
		if(min<60){
			if(min<1){
				displayeTime = "just now";
			}else{
				displayeTime = min+" minutes ago";
			}
		}else if (min<60*24){
			displayeTime = min/60 +" hours ago";
		}else{
			displayeTime = DateUtil.parseDateToString(date, DateUtil.news_sdf);
		}
		return displayeTime;
    }

    public static int daysOfTwo(Date fDate, Date oDate) {
    	if(fDate==null || oDate==null){
    		return 0;
    	}
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(fDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(oDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        return day2 - day1;
    }

    public static List<String> getAllMondays(Date date, int number){
    	Calendar cal =Calendar.getInstance();

    	if (date != null) {
    		cal.setTime(date);
		}

    	cal.setFirstDayOfWeek(Calendar.MONDAY);
    	cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);

    	List<String> listOfMonday = new ArrayList<String>();

    	String monday = date_sdf_statics.format(cal.getTime());
		listOfMonday.add(monday);

		number = number-1;
		if(number>0){
			for (int i = 0; i < number; i ++) {
	    		cal.add(Calendar.DAY_OF_WEEK_IN_MONTH, -1);
	    		monday = date_sdf_statics.format(cal.getTime());
	    		listOfMonday.add(monday);
			}
		}
    	return listOfMonday;
    }

    public static String getLatestMonday(){
    	Calendar cal =Calendar.getInstance();
    	cal.setTime(new Date());
    	// 设置每个星期的第一天是星期一 中国人习惯是星期一是第一天
    	cal.setFirstDayOfWeek(Calendar.MONDAY);
    	cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		String monday = date_sdf_statics.format(cal.getTime());
		return monday;
	}

    /**
     * 根据生日计算年龄
     * @param birthday
     * @return
     * @throws UtilityException
     */
	public static int calculateAge(Date birthday) {
		Calendar nowCalendar = Calendar.getInstance();

		Calendar befoter = Calendar.getInstance();
		befoter.setTime(birthday);

		if (befoter.getTimeInMillis() > nowCalendar.getTimeInMillis()) {
			String errMsg = "birthday is not greater than the current time";
			mLogger.error(errMsg);
			return 0;
		}

		int age = nowCalendar.get(Calendar.YEAR) - befoter.get(Calendar.YEAR);

		if (nowCalendar.get(Calendar.MONTH) >= befoter.get(Calendar.MONTH)) {
			if (nowCalendar.get(Calendar.DAY_OF_MONTH) >= befoter.get(Calendar.DAY_OF_MONTH)) {
				age +=1;
			}
		}

		return age;
	}


	/**
	 * 返回传入的date - x天 的字符串
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String dateSubtract(Date date, int subtractDay) {

		Calendar calendar = Calendar.getInstance();

		if (date != null) {
			calendar.setTime(date);
		}

		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - subtractDay);

		String str = date_sdf.format(calendar.getTime());

		return str;
	}

	/**
	 * 时间转换为CronExpression eg. "0 06 10 15 1 ? 2014"
	 * @param date 时间
	 * @return
	 */
	public static String dateConvertToCronExpression(Date date) {
		return CRONEXPRESSION_FORMAT.format(date);
	}

	/**
	 * 返回两个时间相隔多少天
	 * @param firstDate
	 * @param lastDate
	 */
	public static int differenceInDays(Date firstDate, Date lastDate)  {
		return  Integer.valueOf((lastDate.getTime() - firstDate.getTime()) / (1000 * 60 * 60 * 24) + "");
	}


	/**
	 * 在指定时间基础上增加指定天数
	 * @param dateTime 可空 默认是当前时间
	 * @param days 指定天数
	 * @return
	 */
	public static Date addDate(Date dateTime, int days) {
		Calendar calendar = Calendar.getInstance();

		if (dateTime != null) {
			calendar.setTime(dateTime);
		}

		calendar.add(Calendar.DAY_OF_MONTH, days);

		return calendar.getTime();
	}

	/**
	 * 取指定时间的周一的日期
	 * @param dateTime
	 * @return eg:2015-09-14
	 */
	public static String getWeekFirstDate(Date dateTime) {
		Calendar calendar = Calendar.getInstance();

		if (dateTime != null) {
			calendar.setTime(dateTime);
		}

		// 设置每个星期的第一天是星期一 中国人习惯是星期一是第一天
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		// 设置成当前星期的星期一
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date firstDate = calendar.getTime();

		return parseDateToString(firstDate, date_sdf);
	}

	/**
	 * 取指定时间的上周一的日期
	 * @param dateTime
	 * @return eg:2015-09-07
	 */
	public static String getPreWeekFirstDate(Date dateTime) {
		Calendar calendar = Calendar.getInstance();

		if (dateTime != null) {
			calendar.setTime(dateTime);
		}

		// 设置每个星期的第一天是星期一 中国人习惯是星期一是第一天
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		// 设置成当前星期的星期一
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, -1);
		Date firstDate = calendar.getTime();

		return parseDateToString(firstDate, date_sdf);
	}

	/**
	 * 返回指定时间星期一 和 星期天 带时间
	 * @param date
	 * @return Map{monday: 星期1 , sunday: 星期 天}
	 */
	public static Map<String, Date> getCurrentWeekFLDate(Date date){
		Map<String, Date> map = new HashMap<String, Date>();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		// 设置每个星期的第一天是星期一 中国人习惯是星期一是第一天
		calendar.setFirstDayOfWeek(Calendar.MONDAY);

		// 设置成当前星期的星期一
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

//		calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date firstDate = calendar.getTime();

		// 设置成当前星期的星期天
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		Date lastDate = calendar.getTime();

		map.put("monday", firstDate);
		map.put("sunday", lastDate);

		return map;
	}
	/**
	 * 周一-1,周日-7
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date){
		int[] week = {0,7,1,2,3,4,5,6};
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return week[c.get(Calendar.DAY_OF_WEEK)];
	}

	public static Date getThreeWeekLaterSunday(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.WEEK_OF_YEAR, 3);
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
		day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 7);
		return c.getTime();
	}

	public static int getDaysOfTwoDate(Date date1,Date date2){
		date1 = DateUtil.parseStringToDate(date_sdf.format(date1), date_sdf);
		date2 = DateUtil.parseStringToDate(date_sdf.format(date2), date_sdf);
		return Math.abs((int)((date2.getTime()-date1.getTime())/(1000*60*60*24)));
	}
	/**
	 *
	 * @param args
	 * @throws UtilityException
	 */
	public static void main(String[] args) {
//		Date data = convertTimeByTimezone(new Date(),"Australia/Darwin");
//		System.out.println(convertDateToString(new Date(),"Australia/Darwin"));
		List<String> listOfMonday = getAllMondays(parseStringToDate("20150926",date_sdf_statics),8);
		for(int i=0;i<listOfMonday.size();i++){
    		System.out.println(listOfMonday.get(i));
    	}

//		System.out.println(claculateAge("19900615"));
//		System.out.print(BIRTHDAYPATTERN.matcher("19900615").matches());

//		String str = dateSubtract(new Date("2015/7/1"), 3);

//		System.out.println(str);

//		System.out.println(addDate(null, -1));
	}
}
