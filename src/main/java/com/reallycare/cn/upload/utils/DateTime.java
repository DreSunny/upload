package com.reallycare.cn.upload.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/11/27.
 */
public class DateTime {


    //获取近6年
    public static List<String> getYear(int yearNum){
        List<String> resultList = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        int nowYear = cal.get(Calendar.YEAR);
        for(int i = 0 ;i< yearNum;i++){
            int lastYear = nowYear - i;
            resultList.add(String.valueOf(lastYear));
        }
        return resultList;
    }


    //获取12个
    public static List<String> getMonth(int month){
        List<String> resultList = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        for(int i = 0 ;i< month;i++){
            int lastYear = month - i;
            resultList.add(String.valueOf(lastYear));
        }
        return resultList;
    }

    public static void main(String[] args) {
        //年
        List<String> year = getYear(6);
        System.out.println(year);

        //月
        List<String> month = getMonth(12);
        System.out.println(month);

        //日
        List<String> s =  getDay(2018,2);
        System.out.println(s);
    }

     //当前月有多少天
    public static List<String> getDay(int year ,int month){
      Calendar c = Calendar.getInstance();
       c.set(year, month, 0); //输入类型为int类型e.CHINA);
       int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
         List<String> resultList = new ArrayList<String>();
         Calendar cal = Calendar.getInstance();
        for(int i = 0 ;i< dayOfMonth;i++){
            int lastYear = dayOfMonth - i;
            resultList.add(String.valueOf(lastYear));
        }
       return resultList;

    }


    /*
      * 将时间戳转换为时间
      */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
      * 将时间戳转换为时间
      */
    public static Date stampToDateTime(String s){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        return date;
    }

    /**
     * String 返回 date ，异常返回当前时间date
     * @param string
     * @return
     */
    public static Date stringGetDate (String string){
        SimpleDateFormat sdf = null;
        if(string.contains(":")){
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }else{
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        Date date = null;
        try {
            date = sdf.parse(string);
        } catch (ParseException e) {
            date = new Date();
        }
        return date;
    }



}
