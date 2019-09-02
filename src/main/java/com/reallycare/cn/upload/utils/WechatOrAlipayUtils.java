package com.reallycare.cn.upload.utils;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 *
 * <p>Title: JSON-XML转换工具</p>
 * <p>Title: XML-MAP转换工具</p>
 * <p>desc:
 * @author zhijun liu
 * @time 上午11:20:40
 * @version 1.0
 * @since
 */
public class WechatOrAlipayUtils {

        public static String xml2JSON(String xml){

            return new XMLSerializer().read(xml).toString();
        }

        public static String json2XML(String json){
            JSONObject jobj = JSONObject.fromObject(json);
            String xml =  new XMLSerializer().write(jobj);
            return xml;
        }

   /**
    * XML-MAP 转换工具
    * wechat
    * */
    public static Map<String,Object> parseBodyXml2Map(String xml){
        Map<String,Object> map = new HashMap<String,Object>();
        Document doc = null;
        try{
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            List<Element> list = rootElt.elements();//获取根节点下所有节点
            for (Element element : list) {  //遍历节点
                map.put(element.getName(), element.getText()); //节点的name为map的key，text为map的value
            }
        }catch(DocumentException e){
            e.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    /**
     * String  requestBody -MAP 转换工具
     *
     * alipay
     * */
    public static Map<String,Object> parseRequestBody2Map(String alipay){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            String[] strs = alipay.split("&");
            for(String s:strs) {
                String[] ms = s.split("=");
                map.put(ms[0], ms[1]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
   /**
    * MAP-JSONObject 转换工具
    * */
    public static JSONObject map2JsonObj(Map<String, Object> map) {
        JSONObject resultJson = new JSONObject();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            resultJson.put(key, map.get(key));
        }
        return resultJson;
    }

        public static void main(String[] args) {
            String requestBody ="<SstResponse>"
                    + "<Code>2000000</Code>"
                    + "<Message>你当前没有需要缴费的信息！</Message>  "
                    + "<Total></Total>"
                    + "<ReturnValue size=''></ReturnValue>"
                    + "</SstResponse>";

//                       String xml = json2XML(requestBody);
//                      System.out.println("xml = "+xml);
            //            String json = xml2JSON(xml);
            //            System.out.println("json="+json);


            //微信回调处理
//            String requestBody = "<xml><appid><![CDATA[wx2d5ee03be87e60e0]]></appid>" +
//                    "<bank_type><![CDATA[CFT]]></bank_type>" +
//                    "<cash_fee><![CDATA[1500]]></cash_fee>" +
//                    "<fee_type><![CDATA[CNY]]></fee_type>" +
//                    "<is_subscribe><![CDATA[Y]]></is_subscribe>" +
//                    "<mch_id><![CDATA[1264302201]]></mch_id>" +
//                    "<nonce_str><![CDATA[O3sBNE0KJwQkx8p8]]></nonce_str>" +
//                    "<openid><![CDATA[oCTxTw-4cV3MeldyZjhmcHEYdQ-k]]></openid>" +
//                    "<out_trade_no><![CDATA[20170803103123714829]]></out_trade_no>" +
//                    "<result_code><![CDATA[SUCCESS]]></result_code>" +
//                    "<return_code><![CDATA[SUCCESS]]></return_code>" +
//                    "<sign><![CDATA[5AC9B85F9BDD07E7D189A81B2A675A46]]></sign>" +
//                    "<sub_mch_id><![CDATA[1343726001]]></sub_mch_id>" +
//                    "<time_end><![CDATA[20170803103131]]></time_end>" +
//                    "<total_fee>1500</total_fee>" +
//                    "<trade_type><![CDATA[JSAPI]]></trade_type>" +
//                    "<transaction_id><![CDATA[4000672001201708034192745358]]></transaction_id>" +
//                    "</xml>";
//
//            Map<String,Object> map = parseBodyXml2Map(requestBody);
//            System.out.println("map="+map);
//            JSONObject resultJson = map2JsonObj(map);
//            String outTradeNo = resultJson.getString("out_trade_no");
//            String trade_no = resultJson.getString("transaction_id");
//            System.err.println(outTradeNo +"=======" +trade_no);
//            System.out.println("resultJson="+resultJson.toString());

            //支付宝回调处理
//           String requestBody= "buyer_id=2088422200575993&trade_no=2017080321001004990294875921&body=20880071380745692251779800319999%3B&notify_time=2017-08-03+10%3A50%3A45&use_coupon=N&subject=20170803103606641429&sign_type=RSA&is_total_fee_adjust=N&notify_type=trade_status_sync&out_trade_no=20170803103606641429&trade_status=TRADE_SUCCESS&gmt_payment=2017-08-03+10%3A36%3A19&sign=flFiOlt6QpwT6g53EZHKJ9k3s83sXEKXVJDV7H2hEd8ozVNxHC4B7A9ZkejPdQByji%2BFXjulj8l1l5Flr4P%2BVXuJz3G7S%2F9hPdNSSHumgrYosGfhXRqG%2BvSgU2d1l9cuJpOGWaS%2FMtpowvnRznCbq%2FeUw3%2BEDshjaQXiCeNyNRs%3D&buyer_email=15034547499&gmt_create=2017-08-03+10%3A36%3A19&price=2000.00&total_fee=2000.00&quantity=1&seller_id=2088421538764994&seller_email=reallycare_541yy%40163.com&notify_id=3087762e0094bef2b503b39fd307300nn2&payment_type=1";
//
//           Map<String,Object> map = parseRequestBody2Map(requestBody);
//            System.out.println("map="+map);
//            JSONObject resultJson = map2JsonObj(map);
//            String outTradeNo = resultJson.getString("out_trade_no");
//            String trade_no = resultJson.getString("trade_no");
//            System.err.println(outTradeNo +"=======" +trade_no);
//            System.out.println("resultJson="+resultJson.toString());


        }



}
