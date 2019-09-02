/**
 * 
 */
package com.reallycare.cn.upload.constants;

/**
 * @author lkp
 *
 */
public class Constant {

    private static ApplicationProperties properties = new ApplicationProperties();

    /**
     * 定义编码格式 UTF-8
     */
    public static final String CHARSET_UTF8 = "UTF-8";

    /**
     * 定义编码格式 GBK
     */
    public static final String CHARSET_GBK = "GBK";
    /**
     * 定义编码格式 GB2312
     */
    public static final String CHARSET_GB2312 = "gb2312";

    /**
     * 定义编码格式 ISO8898-1
     */
    public static final String CHARSET_ISO = "ISO-8859-1";

    //预约挂号支付类型
    public static final String BOOKING_PAY_ITEM_NAME = "预约挂号";

    //待缴费项支付类型
    public static final String PAYMENT_PAY_ITEM_NAME = "待缴费项";

    //待缴费项支付类型
    public static final String IN_HOSPITAL_PAY_ITEM_NAME = "住院押金";

    //待缴费项支付类型
    public static final String PAYMENT_PAYLIST_ITEM_NAME = "待缴费项批量支付";

    public static final String WEI_XIN_PAY_URL = "http://www.reallycare.cn/medicaljinyi/weixin/weixinCommonPay";

    public static final String ERRORPAGE = "http://www.reallycare.cn/";

    //近医通微信退款路径
    public static final String  JYT_WEIXIN_REFUNDURL   = "http://itf.reallycare.cn:8095/api/WxApi/WxRefund_new";
    //单独商户号退款路径
    public static final String  WEIXIN_REFUNDURL   = "http://itf.reallycare.cn:8095/api/WxApi/WxRefund_Common";

    /** 金额为分的格式 */
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

    public static final Long LONG_DEFAULT_VALUE = 0L;

    public static final Double DOUBLE_DEFAULT_VALUE = 0.00;

    public static final String UNDERLINE = "_";

    public static final String API_WILDCARD_MATCH_MARK = "@";

	public static final String API_WILDCARD_MATCH_ONE = "?";
	public static final String API_WILDCARD_MATCH_ANY = "*";

    public static final String API_WILDCARD_MATCH_TAG = "^";
	
	public static final int API_REQUEST_TIME_OUT_DEFAULT = 10000;
	public static final int API_REQUEST_TIME_OUT_LONG = 60000;	// 默认的长超时时间

	public static final int API_MAX_REQUEST_FAILED_DEFAULT_COUNT = 60;//api最大请求失败默认次数,超过则报警
	
	public static final String NULL = "null";
	
	public static final String NULL_DATE = "0000-00-00 00:00:00";
	
	public static final String HTTP_ERROR_CODE_502 = "502";
	public static final String HTTP_ERROR_CODE_504 = "504";

    public static final String YRAR_MONTH_STR = "yyyyMM";
    public static final String DATE_STR = "yyyyMMdd";

    public static final String START_TIME_POINT = " 00:00:00";

    public static final String END_TIME_POINT = " 23:59:59";

    public static final String HOUR_STR = "HH";
    public static final String MINUTE_STR = "MM";

    public static final String AM_NUM = "10";

    public static final String PM_NUM = "30";

    public static final String W_APP_ID = "wx2d5ee03be87e60e0";

    public static final String W_APP_SECRET = "fe7a01315f4d1cfa1c90af271bec4c22";

    //获取近医通token路径
    public static final String W_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    //获取近医通用户增减数据路径
    public static final String W_USER_SUMMARY_URL = "https://api.weixin.qq.com/datacube/getusersummary";

    //获取关注近医通全部人数数量
    public static final String W_USER_TOTAL_COUNT = "https://api.weixin.qq.com/cgi-bin/user/get";

    //获取近医通token路径
    public static final String W_WECHAT_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=";

    //微信推送路径
    public static final String W_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
    //获取近医通用户头像
    public static final String W_USER_HEAD_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=";

    //微信模板消息
    public static final String W_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    //近医通accessToken
    public static String ACCESSTOKEN = "";

    public static final String REFUND_STR = "%s%03d";

    public static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";

//    public static final String WEINING_URL = "https://bp.winning-health.com.cn:15574/winbx/p?";
//    public static final String WEINING_URL_TEST = "https://bp.winning-health.com.cn:15574/winbx/p?";

    public static final String WEINING_URL = "https://bp.winning-health.com.cn:15571/winbx/p?";

    public static String getPayParam(String flag) {
        // TODO Auto-generated method stub
        return properties.getProperty(flag);
    }

    public static final String VERTICAL_LINE = "|";

    public static final String LIANHEJIAOFEI_ORDER_STATUS = "lianhejiaofei";

    public static final String COMMA = ",";

    public static final String BOOK_ORDER_STATUS = "ghkey";
    //请求地址(测试)：
    public static final String PING_AN_GUA_HAO_URL = "https://test1-city.pingan.com.cn/city/healthcare/sxJyRegistration/payCallBackNotify";



}
