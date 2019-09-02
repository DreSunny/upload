package com.reallycare.cn.upload.utils;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhoupeiwu
 * @Title: JytUtils
 * @ProjectName medical
 * @Description: TODO
 * @date 2019-05-2210:37
 */
public class JytUtils {

    private static final Logger logger = LoggerFactory.getLogger(JytUtils.class);

    private static final String api = "http://plus.reallycare.cn:8080/backend/api/v1/";

    /**
     * 根据OpenId获取用户
     * @param openId
     * @return
     */
    public static JSONObject getUserByOpenId(String openId){
        JSONObject result = new JSONObject();
        try{
            String url = api+"user/getUserByOpenId/"+openId;
            String resultStr = HttpClient.get(url);
            result = JSONObject.fromObject(resultStr);
        }catch (Exception ex){
            logger.info("根据OpenId获取用户出现异常:"+ex.getMessage());
        }
        return result;
    }


    /**
     * 核验用户是否注册过近医通，没注册就往近医通添加就诊人
     * @param patient
     * @return
     */
    public static void checkJytUser(Patient patient){
        try{
            String url = api+"user/getByOpenIdAndIdCardAndName/"+patient.getOpenId()+"/"+patient.getIdcard()+"/"+patient.getName();
            String resultStr = HttpClient.get(url);
            logger.info("校验用户近医通注册获取用户信息返回结果:"+resultStr);
            JSONObject result = JSONObject.fromObject(resultStr);
            if(result.getInt("errorCode")==-1){
                //没有就添加就诊人
                url = api+"user/newAddUserByTen";
                JSONObject user = new JSONObject();
                user.put("name",patient.getName());
                user.put("idCard",patient.getIdcard());
                user.put("mobile",patient.getTel());
                user.put("gender","1");
                user.put("detailedAddress","");
                user.put("address","");
                user.put("relationship","其他");
                user.put("type","成人");
                user.put("birthday","");
                user.put("openId", patient.getOpenId());
                user.put("address","");
                user.put("isDefault",0);
                resultStr = HttpClient.postJson(url,user.toString());
                logger.info("校验用户近医通注册新增用户信息返回结果:"+resultStr);
            }
        }catch (Exception ex){
            logger.info("根据OpenId获取用户出现异常:"+ex.getMessage());
        }
    }
}
