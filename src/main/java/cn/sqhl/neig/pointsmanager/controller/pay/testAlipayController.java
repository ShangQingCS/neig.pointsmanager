package cn.sqhl.neig.pointsmanager.controller.pay;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.sunchin.payment.PayUtil;
import cn.sunchin.payment.alipay.ReturnParameter;
import cn.sunchin.payment.alipay.util.ConstructObject;
import cn.sunchin.payment.alipay.util.alipayHelp;
import cn.sunchin.payment.alipay.vo.app.AppBusinessParameter;
import cn.sunchin.payment.alipay.vo.web.WebBasicParameter;
import cn.sunchin.payment.alipay.vo.web.WebBusinessParameter;

@Controller
@RequestMapping("Pay")
public class testAlipayController{
	
	/***
	 * APP 获取支付宝支付订单信息
	 * @param model
	 * @param request
	 * @return
	 * @throws  
	 * @throws InstantiationException 
	 */
	@ResponseBody
	@RequestMapping("Appaliregister")
	public JSONObject loginAuthentication( HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String encoding = request.getCharacterEncoding();
		InputStream requestjson = request.getInputStream();
		String locationsJSONString=IOUtils.toString(requestjson,encoding);
	    JSONObject requestString=JSONObject.parseObject(locationsJSONString);
	    Map<String, String> paramsinfo=(Map<String, String>)JSON.parseObject(locationsJSONString, Map.class);
	    paramsinfo.put("out_trade_no", PayUtil.getOutTradeNo());
//	    Map<String, String> map=(Map<String, String>)JSON.parseObject(locationsJSONString, Map.class);
//		Map<String, String> paramsinfo=PayUtil.getPraMap(map);
		AppBusinessParameter bp=ConstructObject.getAppBusinessParameter(paramsinfo);
		String orderInfo=alipayHelp.getAppOrderInfo(bp);
		JSONObject job=new JSONObject();
		job.put("orderinfo", orderInfo);
		return job;
	}
	
	/***
	 * 接受支付宝异步返回
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("AlipayNotify")
	public String alipayNotify(HttpServletResponse response,HttpServletRequest request){
		Map<String, String> paramsinfo=PayUtil.getPraMap(request.getParameterMap());
		Map<String, Object> map=ConstructObject.CheckReturn(paramsinfo);
		boolean verifyResult=Boolean.parseBoolean(map.get("verify_result")+"");
		ReturnParameter rp=(ReturnParameter)map.get("returnparameter");
		JSONObject jsonobj=new JSONObject();
		String result="";
		if(verifyResult){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(rp.getTrade_status().equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			} else if (rp.getTrade_status().equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				
			result="success";	//请不要修改或删除

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			result="fail";
		}
		return result;
	}
	
	/***
	 * 即时到账 获取页面提交
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("Payali")
	public String alipayNowPay(HttpServletResponse response,HttpServletRequest request,Model model) throws IOException{
		String encoding = request.getCharacterEncoding();
		InputStream requestjson = request.getInputStream();
		String locationsJSONString=IOUtils.toString(requestjson,encoding);
	    JSONObject requestString=JSONObject.parseObject(locationsJSONString);
	    Map<String, String> paramsinfo=(Map<String, String>)JSON.parseObject(locationsJSONString, Map.class);
		WebBusinessParameter wbp= ConstructObject.getWebBusinessParameter(paramsinfo);
		String shtmlText="";
		shtmlText = alipayHelp.getWebFrom(WebBasicParameter.getInstance(),wbp);
		model.addAttribute("submitbotton",shtmlText); 
		return "/jsp/pay/submit";
	}
}
