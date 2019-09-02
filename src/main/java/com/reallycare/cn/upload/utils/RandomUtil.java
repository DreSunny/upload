package com.reallycare.cn.upload.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomUtil {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static Random random = null;
	/**
	 * 生成随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		String string = "abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuffer sb = new StringBuffer();
		int len = string.length();
		for (int i = 0; i < length; i++) {
			sb.append(string.charAt(getRandom(len - 1)));
		}
		return sb.toString();
	}
	/**
	 * 生成随机数
	 * @param count
	 * @return
	 */
	public static int getRandom(int count) {
	   return (int) Math.round(Math.random() * (count));
	}

    /**
     * 将日期转化为字符串
     * @return
     */
    public static String DateToString(Date date, String formatString) {
        SimpleDateFormat formate = new SimpleDateFormat(formatString);
        return formate.format(date);
    }

	/**
	 * 获取min - max之间的随机整数
	 * @param min
	 * @param max
	 * @return
	 */
	public static int random(int min,int max){
		Random random = getInstance();
		int s = random.nextInt(max)%(max-min+1) + min;
		return s;
	}
	private static Random getInstance(){
		if(random == null){
			random = new Random();
		}
		return random;
	}

}
