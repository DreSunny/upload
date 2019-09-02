package com.reallycare.cn.upload.utils;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/4.
 */
public class ClientUtil {

    private final static Logger logger = LoggerFactory.getLogger(ClientUtil.class);
    public static String prefix="jsonData=";

    public static String httpRequest = "http://itf.reallycare.cn:8099/api/HisAdaptation/";

    public static String requestJson(String encodeBody,String head64)
    {
        String resultJson = prefix+"{\"head\":\""+head64+"\",\"body\":\""+encodeBody+"\"}";
        return resultJson;
    }
    public static String getJsonValueByKey(String responseJson, String key) {
        JSONObject jsonObject = JSONObject.fromObject(responseJson);

        if(StringUtils.isEmpty(key)) {
            key = "result";
        }

        String resultJson = jsonObject.getString(key);
        return resultJson;
    }

    public static String httpResponseJson(String methodName, String requestJson, String uri) {
        String resultJson = "";
        if(uri == null) {
            resultJson = HttpClient.post(httpRequest+methodName, requestJson);
        } else {
            if("http://59.48.37.90:8088/doReqToHis/".equals(uri) || "http://60.220.234.38:8000/doReqToHis/".equals(uri)) {
                resultJson = HttpClient.postJson(uri+methodName, requestJson);
            } else {
                resultJson = HttpClient.post(uri+methodName, requestJson);
            }

        }
//        logg.error(uri + "httpResponseJson方法返回结果" + resultJson);
        if (StringUtils.isNotEmpty(resultJson)) {
            try{
                resultJson = new String(resultJson.getBytes(), "UTF-8");
            } catch (Exception e){
                logger.info("接口返回结果编码异常" + e);
            }
        }
        return resultJson;
    }

    public static Map<String,String> jsonResult(String body, String head64, String methodName, String resultKey, String uri) {
        String resultJson = "";
        String bodd = body;
        body = new Base64().encode(body.getBytes());
        logger.info("加密后的入参body为"+body);
        resultJson = requestJson(body,head64);
        if (methodName.contains("PayChargeCommon") || methodName.contains("GetPatCode")){
            resultJson = resultJson.replace("+", "%2B");
        }

        try{
            resultJson = new String(resultJson.getBytes(), "UTF-8");
            logger.info("加密后的入参resultJson为"+resultJson);
        } catch (Exception e){
//            logger.error("请求发送的参数编码格式异常" + e);
        }
//        logger.error(bodd + "jsonResult请求发送的参数：" + resultJson);
        resultJson = httpResponseJson(methodName, resultJson, uri);

        try {
            //有些医院返回的接口参数未被解析，需要处理一下
            if(resultJson.contains("body"))
            {
                JSONObject jsonObject = JSONObject.fromObject(resultJson);
                resultJson = (String)jsonObject.get("body");
            }

        } catch (Exception e) {
            e.printStackTrace();
            if(resultJson.contains("body"))
            {
                //解密
                resultJson = resultJson.replaceAll("\r|\n", "");

                resultJson = resultJson.replace("%2B", "+");
                JSONObject jsonObject = JSONObject.fromObject(resultJson);
                resultJson = (String)jsonObject.get("body");
            }
        }

        resultJson = new Base64().decodeResultJson(resultJson);
        logger.info("接口返回"+ resultJson);

        //有些医院返回的json不正确，多了个{}
        resultJson = resultJson.replaceAll("\\{\\{","\\{");

//        logger.error(bodd + "jsonResult getJsonValueByKey方法之前返回结果" + resultJson);
        String resultCode = "";
        String resultMsg = "";

        resultCode = getJsonValueByKey(resultJson,"code");
        resultMsg = getJsonValueByKey(resultJson,"msg");



        String resultData = "";

        if (resultKey.equals("")){
            JSONObject object = JSONObject.fromObject(resultJson);
            object.remove("code");
            object.remove("msg");
            resultData = object.toString();
        } else {
            if(!resultJson.contains(resultKey))
            {
                JSONObject object = JSONObject.fromObject(resultJson);
                object.remove("code");
                object.remove("msg");

                resultData = object.toString();

            }
            else
            {
                resultData = getJsonValueByKey(resultJson,resultKey);
            }
        }



        Map<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("resultCode",resultCode);
        resultMap.put("resultMsg",resultMsg);
        resultMap.put("resultData",resultData);

        return resultMap;
    }

}
