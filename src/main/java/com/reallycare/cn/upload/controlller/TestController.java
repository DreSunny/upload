package com.reallycare.cn.upload.controlller;

import com.reallycare.cn.upload.utils.Result;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 孙宇豪
 * @Date: 2019/8/7 10:14
 * @Description: TODO
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("test")
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Integer count=0;

    @GetMapping("httptest")
    public String httptest() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/upload/test/test");
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000).setConnectionRequestTimeout(1000)
                .setSocketTimeout(5000).build();
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("得到的结果:" + response.getStatusLine());//得到请求结果
        HttpEntity entity = response.getEntity();//得到请求回来的数据
        String s = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(s);
        return s;
    }

    @GetMapping("test")
    public String test(ModelMap model) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("call testMethod method.");
        model.addAttribute("name", "test method");
        return "test";
    }

    @GetMapping("getData")
    public Result getData(){
        try {
            count++;
            MongoListDate date=new MongoListDate();
            date.setId("test001");
            date.setCreateTime("2019-08-09");
            date.setOpenId("wechatoiuqljauioth123iopsd");
            date.setUserId("safq35");
            List<MongoListDate> dates=new ArrayList<>();
            for (int i=0;i<=2;i++){
                dates.add(date);
            }
            long startTime = System.currentTimeMillis();
            while (true){
                long endTime = System.currentTimeMillis();
                long ms = endTime - startTime;
//                logger.info("当前阻塞第"+ms+"毫秒");
                if (ms>(10*1000)){
                    break;
                }
            }
            logger.info("请求成功，第"+count+"位访客");
            return new Result(dates);

        }catch (Exception e){
            return new Result(-1,"请求失败");
        }
    }
}
