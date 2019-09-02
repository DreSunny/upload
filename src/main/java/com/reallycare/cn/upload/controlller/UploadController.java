package com.reallycare.cn.upload.controlller;

import com.reallycare.cn.upload.utils.FileUtil;
import com.reallycare.cn.upload.utils.ImageUtil;
import com.reallycare.cn.upload.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * @author 孙宇豪
 * @Description: TODO 上传图片接口
 * @date 2019/06/3
 */
@SuppressWarnings("ALL")
@CrossOrigin
@RestController
@RequestMapping("upload")
public class UploadController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String uploadUrl="http://39.106.39.184";

    /**
     * 上传图片信息
     *
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public Result uploadImageSY(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        try {
            long s1 = System.currentTimeMillis();
            String fileName = file.getOriginalFilename();
            logger.info("fileName-->" + fileName);
            //判断文件和后缀
            if (fileName.contains("png")) {
                fileName = UUID.randomUUID().toString() + ".png";
            } else if (fileName.contains("jpg")) {
                fileName = UUID.randomUUID().toString() + ".jpg";
            } else if (fileName.contains("jpeg")) {
                fileName = UUID.randomUUID().toString() + ".jpeg";
            } else {
                return new Result(-1, "该文件类型尚不支持,本次上传失败!");
            }
            String server = request.getServerName();//当前服务器地址
            int port = request.getServerPort();//当前服务器端口
            String filePath = "";
            String imgUrl = "";
            String yasuoImgUrl = "";
            if (server.equals("localhost") || server.equals("127.0.01")) {
                filePath = System.getProperty("catalina.home") + "/webapps/images/";// 线上
                imgUrl = "http://" + server + ":" + port + "/images/" + fileName;
                yasuoImgUrl = "http://" + server + ":" + port + "/images/" + fileName;
            }else {
                filePath = System.getProperty("catalina.home") + "/webapps/images/";// 线上
                imgUrl = "http://" + server + ":" + port + "/images/" + fileName;
                yasuoImgUrl = "http://" + server + ":" + port + "/images/" + fileName;
            }
//            filePath="D://images/";
            logger.info("文件路径:" + filePath + imgUrl);
            try {
                FileUtil.uploadFile(file.getBytes(), filePath,  fileName);
//                paintWaterMarkPhoto(filePath+"src"+fileName,filePath+fileName,hospitalName);
            } catch (Exception e) {
                // TODO: handle exception
            }
            long s3 = System.currentTimeMillis();
            logger.info("上传文件用时" + (s3 - s1) + "ms");
            String tomcatfile = "/home/tomcat-9.0/webapps/images/" + fileName;
            //压缩文件
            ImageUtil.reduceImg(tomcatfile, tomcatfile, 1000, 1000, (float) 1);
            long s4 = System.currentTimeMillis();
            logger.info("压缩文件用时" + (s4 - s3) + "ms");
            long s5 = System.currentTimeMillis();
            System.out.print("总共用时" + (s5 - s1) + "ms");
            logger.info("imgUrl====" + imgUrl);
            imgUrl = imgUrl.replace(uploadUrl+":80/", uploadUrl+"8080/");
            return new Result(imgUrl);
        } catch (Exception el) {
            return new Result(-1, "上传失败!");
        }
    }


    /**
     * 上传图片信息base64
     *
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadImageByBase64", method = RequestMethod.POST)
    public Result uploadImageByBase64(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        try {
            long s1 = System.currentTimeMillis();
            String fileName = file.getOriginalFilename();
            logger.info("fileName-->" + fileName);
            fileName = UUID.randomUUID().toString() + ".jpg";
            String server = request.getServerName();//当前服务器地址
            int port = request.getServerPort();//当前服务器端口
            String filePath = "";
            String imgUrl = "";
            String yasuoImgUrl = "";
            if (server.equals("localhost") || server.equals("127.0.01")) {
                filePath = System.getProperty("catalina.home") + "/webapps/images/";// 线上
                imgUrl = "http://" + server + ":" + port + "/images/" + fileName;
                yasuoImgUrl = "http://" + server + ":" + port + "/images/" + fileName;
            }else {
                filePath = System.getProperty("catalina.home") + "/webapps/images/";// 线上
                imgUrl = "http://" + server + ":" + port + "/images/" + fileName;
                yasuoImgUrl = "http://" + server + ":" + port + "/images/" + fileName;
            }
//            filePath="D://images/";
            logger.info("文件路径:" + filePath + imgUrl);
            try {
                FileUtil.uploadFile(file.getBytes(), filePath,  fileName);
//                paintWaterMarkPhoto(filePath+"src"+fileName,filePath+fileName,hospitalName);
            } catch (Exception e) {
                // TODO: handle exception
            }
            long s3 = System.currentTimeMillis();
            logger.info("上传文件用时" + (s3 - s1) + "ms");
            String tomcatfile = "/home/tomcat-9.0/webapps/images/" + fileName;
            //压缩文件
            ImageUtil.reduceImg(tomcatfile, tomcatfile, 1000, 1000, (float) 1);
            long s4 = System.currentTimeMillis();
            logger.info("压缩文件用时" + (s4 - s3) + "ms");
            long s5 = System.currentTimeMillis();
            System.out.print("总共用时" + (s5 - s1) + "ms");
            logger.info("imgUrl====" + imgUrl);
            imgUrl = imgUrl.replace(uploadUrl+":80/", uploadUrl+":8080/");
            return new Result(imgUrl);
        } catch (Exception el) {
            return new Result(-1, "上传失败!");
        }
    }

    /**
     * 上传视频
     *
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadVideo", method = RequestMethod.POST)
    public Result uploadVideo(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        try {
            long s1 = System.currentTimeMillis();
            String fileName = file.getOriginalFilename();
            logger.info("fileName-->" + fileName);
            fileName = UUID.randomUUID().toString() + ".mp4";
            String server = request.getServerName();//当前服务器地址
            int port = request.getServerPort();//当前服务器端口
            String filePath = "";
            String imgUrl = "";
            String yasuoImgUrl = "";
            if (server.equals("localhost") || server.equals("127.0.01")) {
                filePath = System.getProperty("catalina.home") + "/webapps/images/";// 线上
                imgUrl = "http://" + server + ":" + port + "/images/" + fileName;
                yasuoImgUrl = "http://" + server + ":" + port + "/images/" + fileName;
            }else {
                filePath = System.getProperty("catalina.home") + "/webapps/images/";// 线上
                imgUrl = "http://" + server + ":" + port + "/images/" + fileName;
                yasuoImgUrl = "http://" + server + ":" + port + "/images/" + fileName;
            }
//            filePath="D://images/";
            logger.info("文件路径:" + filePath + imgUrl);
            try {
                FileUtil.uploadFile(file.getBytes(), filePath,  fileName);
//                paintWaterMarkPhoto(filePath+"src"+fileName,filePath+fileName,hospitalName);
            } catch (Exception e) {
                // TODO: handle exception
            }
            long s3 = System.currentTimeMillis();
            logger.info("上传文件用时" + (s3 - s1) + "ms");
            String tomcatfile = "/home/tomcat-9.0/webapps/images/" + fileName;
            //压缩文件
            ImageUtil.reduceImg(tomcatfile, tomcatfile, 1000, 1000, (float) 1);
            long s4 = System.currentTimeMillis();
            logger.info("压缩文件用时" + (s4 - s3) + "ms");
            long s5 = System.currentTimeMillis();
            System.out.print("总共用时" + (s5 - s1) + "ms");
            logger.info("imgUrl====" + imgUrl);
            imgUrl = imgUrl.replace(uploadUrl+":80/", uploadUrl+":8080/");
            return new Result(imgUrl);
        } catch (Exception el) {
            return new Result(-1, "上传失败!");
        }
    }

    /**
     * TODO 增加图片水印
     */
    public static void paintWaterMarkPhoto(String srcImagePath, String targerImagePath, String hospitalName) {
        Integer degree = -15;
        OutputStream os = null;
        try {
            Image srcImage = ImageIO.read(new File(srcImagePath));
            BufferedImage bufImage = new BufferedImage(srcImage.getWidth(null), srcImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // 得到画布对象
            Graphics2D graphics2D = bufImage.createGraphics();
            // 设置对线段的锯齿状边缘处理
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(srcImage.getScaledInstance(srcImage.getWidth(null), srcImage.getHeight(null), Image.SCALE_SMOOTH),
                    0, 0, null);
            // 透明度
            int height = ((BufferedImage) srcImage).getHeight();
            int width = ((BufferedImage) srcImage).getWidth();
            System.out.println(height + "-------" + width);
            float alpha = 0.8f;
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 设置颜色和画笔粗细
            graphics2D.setColor(Color.white);
            graphics2D.setStroke(new BasicStroke(30));
            Font font = new Font("SimSun", Font.ITALIC, (height + width) / 50);
            FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(font);
//            int size = font.getSize();
            graphics2D.setFont(font);
            // 绘制图案或文字
            String cont = "此照片仅供" + hospitalName + "病历预约打印使用";
            //获取整个字符串长度
            int i = fm.stringWidth(cont);
            int start = (width - i) / 2;
            String dateStr = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
            int charWidth1 = 8;
            int charWidth2 = 8;
            int halfGap = 12;
            int x = (srcImage.getWidth(null) - cont.length() * charWidth1) / 40;
            int y = (srcImage.getHeight(null) - (charWidth1 + halfGap)) / 2;
            System.out.println(x + "," + y);
            graphics2D.drawString(cont, start,
                    y);
//            graphics2D.drawString(dateStr, x,
//                    y);

//            graphics2D.drawString(cont, 0,
//                    0);
//            graphics2D.drawString(dateStr, 0,
//                    0);

            graphics2D.dispose();

            os = new FileOutputStream(targerImagePath);
            // 生成图片 (可设置 jpg或者png格式)
            ImageIO.write(bufImage, "png", os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
