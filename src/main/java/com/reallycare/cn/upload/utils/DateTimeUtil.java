package com.reallycare.cn.upload.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("ALL")
public class DateTimeUtil {

    public static String YYYYMDToYYYYMMDD(String date) {
        date = date.trim();
        try {
            if(date.contains(":")){
                //System.out.println("0000="+date);
                String [] dates = date.split(" ");
                date = dates[0];
                //System.out.println("1111="+date);
            }
            if(date.contains("/")){
                date = date.replaceAll("/","-");
            }

            String MM = null;
            String day = null;
            String [] datess = date.split("-");
            int yyyy = Integer.parseInt(datess[0]);
            int mm = Integer.parseInt(datess[1]);
            int dd = Integer.parseInt(datess[2]);
            if(mm < 10){
                MM = "0" + mm;
            }else{
                MM = String.valueOf(mm);
            }
            if(dd < 10){
                day = "0" + dd;
            }else{
                day = String.valueOf(dd);
            }
            date = yyyy+"-"+MM+"-"+day;
            //System.err.println(date);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println( date + " error!");
        }
        return date;
    }


    public static void main(String[] args) {

        String date = "2018/8/23 18:00:00";
        YYYYMDToYYYYMMDDHHMMSS(date);

    }

    public static String YYYYMDToYYYYMMDDHHMMSS(String dateTime) {
        dateTime = dateTime.trim();
        try {
            if(dateTime.contains("/")){
                dateTime = dateTime.replaceAll("/","-");
            }
               String [] datess = dateTime.split(" ");
               String date = datess[0];
               String  time = datess[1];
               if(time.contains("0:00:00")){
                   time = "00:00:00";
               }
            String MM = null;
            String day = null;
            String [] datesss = date.split("-");
            int yyyy = Integer.parseInt(datesss[0]);
            int mm = Integer.parseInt(datesss[1]);
            int dd = Integer.parseInt(datesss[2]);
            if(mm < 10){
                MM = "0" + mm;
            }else{
                MM = String.valueOf(mm);
            }
            if(dd < 10){
                day = "0" + dd;
            }else{
                day = String.valueOf(dd);
            }
            dateTime = yyyy+"-"+MM+"-"+day+" "+time;
            System.err.println("日期格式="+dateTime);
       } catch (Exception e) {
            e.printStackTrace();
            System.out.println( dateTime + " error!");
        }
        return dateTime;
    }


    /***
     * 获取当前日期、当月月初日期、月末日期
     * lzj
     * ***/
    public static String monthStartDate() {

        // 获取当前年份、月份、日期
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        int day = cale.get(Calendar.DATE);
        int hour = cale.get(Calendar.HOUR_OF_DAY);
        int minute = cale.get(Calendar.MINUTE);
        int second = cale.get(Calendar.SECOND);
        int dow = cale.get(Calendar.DAY_OF_WEEK);
        int dom = cale.get(Calendar.DAY_OF_MONTH);
        int doy = cale.get(Calendar.DAY_OF_YEAR);

        System.out.println("Current Date: " + cale.getTime());
        System.out.println("Year: " + year);
        System.out.println("Month: " + month);
        System.out.println("Day: " + day);
        System.out.println("Hour: " + hour);
        System.out.println("Minute: " + minute);
        System.out.println("Second: " + second);
        System.out.println("Day of Week: " + dow);
        System.out.println("Day of Month: " + dom);
        System.out.println("Day of Year: " + doy);

        // 获取当月第一天和最后一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstday, lastday;
        // 获取前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());
        // 获取前月的最后一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastday = format.format(cale.getTime());
        System.out.println("本月第一天和最后一天分别是 ： " + firstday + " and " + lastday);

        // 获取当前日期字符串
        Date d = new Date();
        String monthStartDate = format.format(d);
        System.out.println("当前日期字符串1：" + format.format(d));
        System.out.println("当前日期字符串2：" + year + "/" + month + "/" + day + " "
                + hour + ":" + minute + ":" + second);

        return  monthStartDate;
    }

    /**
     * 得到几天前的时间
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 获得指定日期的前一天
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay){
    //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day-1);

        String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }
    /**
     * 获得指定日期的后一天
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay){
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day+1);

        String dayAfter=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    /**
     * @param dateTime 2017-12-16T16:00:00.000Z
     * 时区时间转换
     * */
    public static String timeZone(String dateTime){
        String dateUTC = "";
        try {
//            String date = "2017-12-16T16:00:00.000Z";
            String date = dateTime;
            date = date.replace("Z", " UTC");//注意是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
            Date d = format.parse(date );
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
             dateUTC = sd.format(d); //Date类型转换为其他格式String类型
//            System.out.println(dateUTC.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  dateUTC;
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 根据日期获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShortByDate(Date Date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(Date);
        return dateString;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }
    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @param
     * @return
     */
    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @param
     * @return
     */
    public static String dateToStrsssss(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd HH:mm
     *
     * @param dateDate
     * @param
     * @return
     */
    public static String dateToNewStrs(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = formatter.format(dateDate);
        return dateString;
    }
    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
    /**
     * 得到现在时间
     *
     * @return
     */
    public static Date getNow() {
        Date currentTime = new Date();
        return currentTime;
    }
    /**
     * 提取一个月中的最后一天
     *
     * @param day
     * @return
     */
    public static Date getLastDate(long day) {
        Date date = new Date();
        long date_3_hm = date.getTime() - 3600000 * 34 * day;
        Date date_3_hm_date = new Date(date_3_hm);
        return date_3_hm_date;
    }
    /**
     * 得到现在时间
     *
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    /**
     * 得到现在小时
     */
    public static String getHour() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }
    /**
     * 得到现在分钟
     *
     * @return
     */
    public static String getTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String min;
        min = dateString.substring(14, 16);
        return min;
    }
    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
     *
     * @param sformat
     *             yyyyMMddhhmmss
     * @return
     */
    public static String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * mysql 时间处理
     * yyyy-MM-dd HH:mm:ss
     * 2018-01-18 15:32:00.0
     *
     * @param
     *
     * @return
     */
    public static String getDateTime(Date dataTime) {
        //MySql dateTime .0 处理
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dataTime);

        return dateStr;
    }

      /**
       * 时间戳转换成日期格式字符串   精确到秒的字符串
       * @param
       * @param
       * @return
       */
     public static String timeStamp2Date(String seconds,String format) {
         if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
                 return "";
         }
        if(format == null || format.isEmpty()){
                format = "yyyy-MM-dd HH:mm:ss";
         }

        SimpleDateFormat sdf = new SimpleDateFormat(format);

         return sdf.format(new Date(Long.valueOf(seconds+"000")));
     }
     /**
      * 日期格式字符串转换成时间戳
      * @return
      */
      public static String date2TimeStamp(String date_str,String format){
        try {
             SimpleDateFormat sdf = new SimpleDateFormat(format);
              return String.valueOf(sdf.parse(date_str).getTime()/1000);
        } catch (Exception e) {
              e.printStackTrace();
        }
          return "";
        }

         /**
           * 取得当前时间戳（精确到秒）
           * @return
           */
         public static String timeStamp(){
            long time = System.currentTimeMillis();
            String t = String.valueOf(time/1000);
            return t;
         }

    /**
     * 时间戳转日期
     * @param ms
     * @return
     */
    public static Date transForDate3(Integer ms){
        if(ms==null){
            ms=0;
        }
        long msl=(long)ms*1000;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date temp=null;
        if(ms!=null){
            try {
                String str=sdf.format(msl);
                temp=sdf.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }
    /**
     * 时间戳转日期
     * @param ms
     * @return
     */
    public static Date transForDate(Long ms){
        if(ms==null){
            ms=(long)0;
        }
        long msl=(long)ms*1000;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date temp=null;
        if(ms!=null){
            try {
                String str=sdf.format(msl);
                temp=sdf.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

    /**
     * 根据日期判断是否为上午、中午、下午
     * @param date
     * @return
     * @author
     */
    public static String getDuringByDate(Date date){
        //获取小时
        int hour = date.getHours();
        if (hour >= 0 && hour < 7) {
            return "早上";
        }else if (hour >= 7 && hour < 11) {
            return "上午";
        }else if (hour >= 11 && hour <= 13) {
            return "中午";
        }else if (hour >= 14 && hour <= 18) {
            return "下午";
        }else if (hour >= 19 && hour <= 23) {
            return "晚上";
        }
        return null;
    }

}
