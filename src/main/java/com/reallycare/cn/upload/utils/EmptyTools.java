package com.reallycare.cn.upload.utils;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * @Author: 孙宇豪
 * @Date: 2018/12/13 17:52
 * @Description: TODO 检查多个字符串是否为空
 * @return
 * @Version 1.0
 */
public class EmptyTools {
    public static boolean isNotEmptyBatch(String... strs) {
        for (String str : strs) {
            if (StringUtils.isEmpty(str))
                return false;
        }
        return true;
    }

    public static boolean JsonisNotEmpty(JSONObject jsonObject){
        if (jsonObject!=null && !jsonObject.isEmpty() && !jsonObject.isNullObject()){
            return true;
        }
        return false;
    }
}
