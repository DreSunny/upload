package com.reallycare.cn.upload.utils;

import net.sf.json.JSONObject;
import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;


public class HttpClient {

	private static String jyt_url="http://jc.reallycare.cn:8080/backend/api/v1/";

	public static String post(String urlstr, String data) {
		try {
			
			org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
			HttpPost method = new HttpPost(urlstr);
			method.setHeader("Content-Type","application/x-www-form-urlencoded");
			ByteArrayEntity reqEntity = new ByteArrayEntity(data.getBytes("UTF-8"));
			reqEntity.setContentEncoding("UTF-8");
			method.setEntity(reqEntity);
			HttpResponse response = null;

			response = httpclient.execute(method);
			if (response != null) {
				// HttpEntity resEntity = response.getEntity();
				return EntityUtils.toString(response.getEntity(),"UTF-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String postJson(String urlstr, String data) {
		try {
			org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
			HttpPost method = new HttpPost(urlstr);
			method.setHeader("Content-Type","application/json");
//            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
			ByteArrayEntity reqEntity = new ByteArrayEntity(data.getBytes("UTF-8"));
			reqEntity.setContentEncoding("UTF-8");
			method.setEntity(reqEntity);
			HttpResponse response = null;

			response = httpclient.execute(method);
			if (response != null) {
				// HttpEntity resEntity = response.getEntity();
				return EntityUtils.toString(response.getEntity());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	
	public static String postOfHttps(String urlstr, String data) {
		try {
			org.apache.http.client.HttpClient httpclient = wrapClient(new DefaultHttpClient());
			HttpPost method = new HttpPost(urlstr);
			method.setHeader("Content-Type","application/x-www-form-urlencoded");
			ByteArrayEntity reqEntity = new ByteArrayEntity(data.getBytes("UTF-8"));
			reqEntity.setContentEncoding("UTF-8");
			method.setEntity(reqEntity);
			HttpResponse response = null;

			response = httpclient.execute(method);
			if (response != null) {
				// HttpEntity resEntity = response.getEntity();
				return EntityUtils.toString(response.getEntity());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String get(String urlstr) {
		org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
		HttpGet method = new HttpGet(urlstr);
		method.setHeader("Content-type" , "text/html; charset=utf-8");

		HttpResponse response = null;
		try {
			response = httpclient.execute(method);
			if (response != null) {
				// HttpEntity resEntity = response.getEntity();
				return EntityUtils.toString(response.getEntity());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	public static String getJson(String urlstr) {
		org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
		HttpGet method = new HttpGet(urlstr);
		method.setHeader("Content-type" , "application/json");
		HttpResponse response = null;
		try {
			response = httpclient.execute(method);
			if (response != null) {
				// HttpEntity resEntity = response.getEntity();
				return EntityUtils.toString(response.getEntity());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public static String getOfHttps(String urlstr) {
		long s1 = System.currentTimeMillis();
		org.apache.http.client.HttpClient httpclient = wrapClient(new DefaultHttpClient());
		HttpGet method = new HttpGet(urlstr);
		method.setHeader("Content-type" , "text/html; charset=utf-8");
		HttpResponse response = null;
		try {
			response = httpclient.execute(method);
			long s2 = System.currentTimeMillis();
			
			System.out.println("获取openId，耗时" + (s2 - s1) + "ms");
			if (response != null) {
				// HttpEntity resEntity = response.getEntity();
				return EntityUtils.toString(response.getEntity());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public static void download(String url, String path) throws HttpException {
		org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
		HttpGet method = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpclient.execute(method);
			if (response != null) {
				File storeFile = new File(path);
				FileOutputStream fos = new FileOutputStream(storeFile);
				HttpEntity resEntity = response.getEntity();
				InputStream is = resEntity.getContent();
				byte[] byteArr = new byte[1024];
				// 读取的字节数
				int readCount = is.read(byteArr);
				// 如果已到达文件末尾，则返回-1
				while (readCount != -1) {
					fos.write(byteArr, 0, readCount);
					readCount = is.read(byteArr);
				}
				is.close();
				fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String downloadAmr(String url, String path) throws HttpException {
		org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
		HttpGet method = new HttpGet(url);
		HttpResponse response = null;
		Header[] headers = null;
		try {
			response = httpclient.execute(method);
			headers = response.getHeaders("Content-Type");
			System.out.println(headers[0]);
			if (response != null) {
				File storeFile = new File(path);
				FileOutputStream fos = new FileOutputStream(storeFile);
				HttpEntity resEntity = response.getEntity();
				InputStream is = resEntity.getContent();
				byte[] byteArr = new byte[1024];
				// 读取的字节数
				int readCount = is.read(byteArr);
				// 如果已到达文件末尾，则返回-1
				while (readCount != -1) {
					fos.write(byteArr, 0, readCount);
					readCount = is.read(byteArr);
				}
				is.close();
				fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return headers[0].toString();
	}
	
	
	
	public static String httpGet(String url){
		org.apache.http.client.HttpClient httpclient = wrapClient(new DefaultHttpClient());
		String result = "";
		try {
			// 连接超时
			httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
			// 读取超时
			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
			HttpGet hg = new HttpGet(url);
			// 模拟浏览器
			hg.addHeader("User-Agent","Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31");
			String charset = "UTF-8";
			hg.setURI(new java.net.URI(url));
			HttpResponse response = httpclient.execute(hg);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				charset = getContentCharSet(entity);
				// 使用EntityUtils的toString方法，传递编码，默认编码是ISO-8859-1
				result = EntityUtils.toString(entity, charset);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally {
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

	public static String getContentCharSet(final HttpEntity entity) throws ParseException {
		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}
		String charset = null;
		if (entity.getContentType() != null) {
			HeaderElement values[] = entity.getContentType().getElements();
			if (values.length > 0) {
				NameValuePair param = values[0].getParameterByName("charset");
				if (param != null) {
					charset = param.getValue();
				}
			}
		}

		if (StringUtils.isEmpty(charset)) {
			charset = "UTF-8";
		}
		return charset;
	}
	
	
	/**
     * 避免HttpClient的”SSLPeerUnverifiedException: peer not authenticated”异常
     * 不用导入SSL证书
     * @author shipengzhi(shipengzhi@sogou-inc.com)
     *
     */
    public static org.apache.http.client.HttpClient wrapClient(org.apache.http.client.HttpClient base) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("https", 443, ssf));
            ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
            return new DefaultHttpClient(mgr, base.getParams());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }








	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
          /*  TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();*/
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			// conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("content-type", "application/json");
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			return buffer.toString();
		} catch (ConnectException ce) {
			//log.error("连接超时：{}", ce);
			System.out.println("链接超时"+ce.getMessage());
			throw new RuntimeException(ce);
		} catch (Exception e) {
			//log.error("https请求异常：{}", e);
			System.out.println("请求异常"+e.getMessage());
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static String sendPost(String urlstr, String data) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost method = new HttpPost(urlstr);
//            method.setHeader("Content-Type","application/x-www-form-urlencoded");
			method.setHeader("Accept","application/json");
			method.setHeader("Content-Type","application/json");
//            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
			ByteArrayEntity reqEntity = new ByteArrayEntity(data.getBytes("UTF-8"));
			reqEntity.setContentEncoding("UTF-8");
			method.setEntity(reqEntity);
			HttpResponse response = null;

			response = httpClient.execute(method);
			if (response != null) {
				// HttpEntity resEntity = response.getEntity();
				return EntityUtils.toString(response.getEntity());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 近医通专用GET
	 * @param api
	 * @return
	 */
	public static String JYTGet(String api){
		String result="";
		try {
			System.out.println("【近医通专用GET】开始");
			System.out.println("【近医通专用GET】接口地址:"+jyt_url);
			System.out.println("【近医通专用GET】拼接后的接口地址:"+jyt_url+api);
			result=HttpClient.sendGet(jyt_url+api, "");
			try {
				result=new String(result.getBytes(),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			System.out.println("【近医通专用GET】返回结果:"+result);
			System.out.println("【近医通专用GET】结束");
		} catch (Exception e) {
			System.out.println("【近医通专用GET】请求异常:"+e.getMessage());
			JSONObject object=new JSONObject();
			object.put("success", false);
			object.put("errorCode", -1);
			object.put("errorMessage", "请求异常!");
			result=object.toString();
		}
		return result;
	}

	/**
	 * 近医通专用POST
	 * @param api
	 * @return
	 */
	public static String JYTPost(String api,String param){
		String result="";
		try {
			System.out.println("【近医通专用POST】开始");
			System.out.println("【近医通专用POST】接口地址:"+jyt_url);
			System.out.println("【近医通专用POST】拼接后的接口地址:"+jyt_url+api);
			System.out.println("【近医通专用POST】参数:"+param);
			result=HttpClient.sendPost(jyt_url+api, param);
        	try {
    			result=new String(result.getBytes(),"UTF-8");
    		} catch (UnsupportedEncodingException e) {
    			e.printStackTrace();
    		}
			System.out.println("【近医通专用POST】返回结果:"+result);
			System.out.println("【近医通专用POST】结束");
		} catch (Exception e) {
			System.out.println("【近医通专用POST】请求异常:"+e.getMessage());
			JSONObject object=new JSONObject();
			object.put("success", false);
			object.put("errorCode", -1);
			object.put("errorMessage", "请求异常!");
			result=object.toString();
		}
		return result;
	}
}
