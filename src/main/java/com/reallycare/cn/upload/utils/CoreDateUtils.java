package com.reallycare.cn.upload.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CoreDateUtils {

	private static final Logger logger = LoggerFactory.getLogger(CoreDateUtils.class.getName());
	
	public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_STR = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_STR = "yyyy/MM/dd HH";
	public static final String DATE = "yyyy-MM-dd";
    public static final String DATE_PATTERN_MONGO = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String DATE_PATTERN_STR = "yyyyMMddHHmmssSSS";
    public static final String DATE_PATTERN_LONG_STR = "yyyyMMddHHmmss";

    public static final String START_DATE_STR = " 00:00:00";
    public static final String END_DATE_STR = " 23:59:59";
		
	public static String formatDate(Date date) {
		return formatDate(date, DATE);
	}
	
	public static String formatDateTime(Date date) {
		return formatDate(date, DATETIME);
	}

	public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, pattern, Locale.CHINA);
	}
	
	public static String formatDate(String dateStr, String srcPattern, String desPattern) {
		Date date = parseDate(dateStr, srcPattern);
		if (date == null) {
			return null;
		}
		return formatDate(date, desPattern);
	}

	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, DATE);
	}

	public static Date parseLongDate(String dateStr) {
		return parseDate(dateStr, new String[] {
                DATETIME,
                "yyyy-MM-dd HH:mm:ss.SSS",
        });
	}

    /**
     * 得到一个UTC时间
     * @param date
     * @return UTC时间
     * lkp
     */
    public static Date getUTCDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, -8);
        return cal.getTime();
    }

    /**
     *  Function: 获取UTC时间
     *  @author lkp
     *  @return
     */
    public static String getUTCTime(Date date){
        //1、取得本地时间：
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //2、取得时间偏移量：
        final int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        //3、取得夏令时差：
        final int dstOffset = cal.get(Calendar.DST_OFFSET);
        //4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'").format(cal.getTime());
    }

	public static Date parseDate(String dateStr, String pattern) {
		return parseDate(StringUtils.trim(dateStr), new String[]{pattern});
	}

    public static Date parseDate(String dateStr, String[] patterns) {
        if (dateStr == null) {
            return null;
        }
        try {
            return DateUtils.parseDateStrictly(dateStr, patterns);
        } catch (ParseException e) {
            logger.error("日期转换错误, dateStr={}, pattern={}", dateStr, StringUtils.join(patterns, ","));
            logger.error(e.getMessage(), e);
            return null;
        }
    }

	public static boolean test(String dateStr, String pattern) {
		return test(dateStr, new String[]{pattern});
	}

    public static boolean test(String dateStr, String[] patterns) {
        if (dateStr == null) {
            return false;
        }
        try {
            DateUtils.parseDateStrictly(dateStr, patterns);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
//
//    public static void main(String[] args){
////	    String resTime = "18:07-09:45";
////        String forDateDayStr = "2017-08-28"+" "+resTime.substring(0,resTime.indexOf("-"))+":00";
////	    String two = CoreDateUtils.formatDate(new Date(),CoreDateUtils.DATETIME);
////
////        System.out.println(diffTowFormateDate(forDateDayStr,two));
//        Date a = CoreDateUtils.parseLongDate("2017-10-17 17:20:16");
//        Date b = CoreDateUtils.parseLongDate("2017-10-17 00:00:00");
//        Date c = CoreDateUtils.parseLongDate("2017-10-17 23:59:59");
//        logger.info("aaa" + belongCalendar(a, b, c));
//    }

    /**
     * 返回两个格式化日期相差的分钟数
     * @param one yyyy-MM-dd HH:mm:ss
     * @param two yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long diffTowFormateDate(String one, String two){
        if (StringUtils.isEmpty(one) || StringUtils.isEmpty(two))
            return 0;
        Date dateOne = parseDate(one,CoreDateUtils.DATETIME);
        Date dateTwo = parseDate(two,CoreDateUtils.DATETIME);
        return (dateOne.getTime() - dateTwo.getTime()) / 1000 / 60;
    }

    /**
     * 返回两个日期相差的天数
     * @param one yyyy-MM-dd
     * @param two yyyy-MM-dd
     * @return
     */
    public static long diffTwoDate(String one,String two){
        if (StringUtils.isEmpty(one) || StringUtils.isEmpty(two))
            return 0;
        Date dateOne = parseDate(one,CoreDateUtils.DATE);
        Date dateTwo = parseDate(two,CoreDateUtils.DATE);
        return (dateOne.getTime() - dateTwo.getTime()) / 1000 / 60 / 60 / 24;
    }

    /**
     * 判断time是否在from，to之内
     *
     * @param testDate 指定日期
     * @param fromDate 开始日期
     * @param toDate   结束日期
     * @return
     */
    public static boolean belongCalendar(Date testDate, Date fromDate, Date toDate) {
        if (testDate == null || fromDate == null || toDate == null) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate);
        calendar.add(Calendar.DATE, 1);
        // 时间差处理
        Date startDate = CoreDateUtils.parseLongDate(CoreDateUtils.formatDate(fromDate) + CoreDateUtils.START_DATE_STR);
        Date endDate = CoreDateUtils.parseLongDate(CoreDateUtils.formatDate(calendar.getTime()) + CoreDateUtils.START_DATE_STR);
        boolean afterFlag = testDate.after(startDate);
        boolean beforeFlag = testDate.before(endDate);
        boolean shijiancha = false;
        if (afterFlag && beforeFlag) {
            shijiancha = true;
        }
        return shijiancha;
    }

    /**
     * 计算输入的格式化日期对于当前日期的天数
     * @param forDateDay  yyyy-MM-dd
     * @return
     */
    public static long diffNowDate(String forDateDay){
        Date dateNow = parseDate(formatDate(new Date()));
        Date forDate = parseDate(forDateDay);
        long dateDiff = diffDays(forDate,dateNow);
        return dateDiff;
    }

	/**
	 * 两个时间相隔天数 time1-time2
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static long diffDays(Date time1, Date time2){
        if (time1 == null || time2 == null) {
            return 0;
        }
        return (time1.getTime() - time2.getTime()) / 1000 / 60 / 60 / 24;
	}

    // >= start && < end
    private static boolean springFestivalCheck = false;
    public static Date springFestivalStartDate = parseDate("2015-02-18");
    public static Date springFestivalEndDate = parseDate("2015-02-25");

    public static boolean isDuringSpringFestival() {
        return isDuringSpringFestival(new Date());
    }

    public static boolean isDuringSpringFestival(Date date) {
        if (!springFestivalCheck) {
            return false;
        }
        if (date == null) {
            return isDuringSpringFestival();
        }
        if (date.before(springFestivalStartDate) || date.after(springFestivalEndDate)) {
            return false;
        }

        return true;
    }

    // >= start && < end
    private static boolean worldCupCheck = false;
    public static Date worldCupStartDate = parseDate("2014-06-13");
    public static Date worldCupEndDate = parseDate("2014-07-15");

    public static boolean isDuringWorldCup() {
        return isDuringWorldCup(new Date());
    }

    public static boolean isDuringWorldCup(Date date) {
        if (!worldCupCheck) {
            return false;
        }
        if (date == null) {
            return isDuringWorldCup();
        }
        if (date.before(worldCupStartDate) || date.after(worldCupEndDate)) {
            return false;
        }

        return true;
    }

    public static String getFetureDate(int past,Date today) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(today.getTime());
        c.add(Calendar.DATE, past);//天后的日期
        Date date= new Date(c.getTimeInMillis()); //将c转换成Date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(date);

        return result;
    }

    public static String getWeekOfDate(Date date) {
        String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekOfDays[w];
    }

    //获取昨天日期
    public static String getYestoday()
    {
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date time=cal.getTime();
        return formatDate(time);
    }

    //获取前天日期
    public static String getTheDayBeforeYestoday()
    {
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-2);
        Date time=cal.getTime();
        return formatDate(time);
    }

    /**
     * 日期转星期
     *
     * @param datetime
     * @return
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        String[] weekDaysCode = { "7", "1", "2", "3", "4", "5", "6" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDaysCode[w];
    }
    public static void main(String[] args) {
        System.out.println(CoreDateUtils.parseLongDate(CoreDateUtils.getFetureDate(1,new Date())));
    }
}
