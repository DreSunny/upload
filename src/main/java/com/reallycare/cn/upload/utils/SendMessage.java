package com.reallycare.cn.upload.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @类名: SendMessage   
 * @说明: 发送短信工具类 
 * @其他: 
 * @作者: 近颐科技 - 周培武 
 * @编写时间: 2017年11月27日 下午3:40:25     
 * @版权说明: 2017 www.reallycare.cn Inc. All rights reserved. 
 * 注意：本内容仅限于北京近颐科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class SendMessage {
	
	public static final Logger logger = LoggerFactory.getLogger(SendMessage.class);

	public static String sendMessage(String tel,String context){
		//发送短信通知
		logger.info("手机号："+tel+"=发送信息："+context);
		String json = "{\"mobile\":\""+tel+"\",\"content\":\""+context+"\"}";
        System.out.println(json);
        String result = HttpClient.postJson("http://www.reallycare.cn/medicaljinyi/commonSmsSample/smsSend", json);
		logger.info("短信通知的返回值："+result);
		return result;
	}
	
	public static void main(String[] args) {
		StringBuilder message=new StringBuilder();
		message.append("【近颐科技】");
		message.append("您好！退费成功！,");
		message.append("就诊人：,");
		message.append("项  目：处方,");
		message.append("社  区：,");
		message.append("缴费时间：,");
		message.append("温馨提示：如有疑问及时拨打客服电话4008760356，感谢您的使用！");
		sendMessage("13683549821", message.toString());
		
	}
}
