package com.reallycare.cn.upload.utils;

import java.util.Date;

public class AgeUtil {

	public static String checkAge(String idcard){
		String birthyear = idcard.substring(6, 10);
		System.out.println(birthyear);
		String nowYear = CoreDateUtils.formatDate(new Date(), "yyyy");
		int b = Integer.valueOf(birthyear);
		int n = Integer.valueOf(nowYear);
		int m = n-b;
		return String.valueOf(m);
	}
	
	
	public static String getBirthday(String idcard){
		String year = idcard.substring(6, 10);
		String month = idcard.substring(10,12);
		String day = idcard.substring(12, 14);
		String birthday = year+"-"+month+"-"+day;
		return birthday;
	} 
	
	
	public static String getSex(String idcard){
		String idkey = idcard.substring(16, 17);
		int m =Integer.valueOf(idkey);
		m = m%2;
		String sex = "";
		if(m == 0){
			sex = "女";
		}else{
			sex = "男";
		}
		return sex;
	}
	
}
