package cn.sqhl.neig.pointsmanager.core.pay.alipay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

public class alipayUtil {
	private static String CHARSET="UTF-8";
	
	private static AlipayClient alipayClient =null;
	
	/***
	 * 单例模式获取全局唯一 alipayclient
	 * @param alipayrequest
	 * @param appid
	 * @param appprivatekey
	 * @param alipaypublickey
	 * @return
	 */
	public static AlipayClient getAlipayClient(String alipayrequest,String appid,String appprivatekey,String alipaypublickey){
		if (alipayClient == null) {  
		        alipayClient =  new DefaultAlipayClient(alipayrequest,appid,appprivatekey,"json",CHARSET,alipaypublickey); 
		}  
		return alipayClient;  
	}
	
	/**
	 * 用于生成唯一 tradeNo
	 * @return
	 */
	private static String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);
		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

	/**
	 * 用于app创建订单信息
     * create the order info. 创建订单信息--------支付宝
     */
    public static String getOrderInfo(String subject, 
    		String body, String price,String merchantUID,
    		String merchant_account,String domain,String notify_url) {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + merchantUID + "\"";
        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + merchant_account + "\"";
        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";
        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";
        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";
        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";
        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + domain+notify_url + "\"";
        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";
        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";
        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";
        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";
        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";
        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";
        return orderInfo;
    }

}
