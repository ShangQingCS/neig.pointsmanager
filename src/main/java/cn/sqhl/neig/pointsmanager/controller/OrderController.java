package cn.sqhl.neig.pointsmanager.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sqhl.neig.pointsmanager.core.pay.alipay.alipayUtil;
import cn.sqhl.neig.pointsmanager.po.NsAddress;
import cn.sqhl.neig.pointsmanager.po.NsOrder;
import cn.sqhl.neig.pointsmanager.service.AddressService;
import cn.sqhl.neig.pointsmanager.service.GoodsService;
import cn.sqhl.neig.pointsmanager.service.OrderService;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sunchin.payment.alipay.util.ConstructObject;
import cn.sunchin.payment.alipay.util.alipayHelp;
import cn.sunchin.payment.alipay.vo.app.AppBusinessParameter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


@Controller
@RequestMapping("/order")
public class OrderController extends ContextInfo{
	private Logger logger=LogManager.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private GoodsService goodsService;
	
	@ResponseBody
	@RequestMapping("/search")
	public JSONObject queryGoods(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="userid",required=false) Long userid,
			@RequestParam(value="orderid",required=false) Long orderid,
			@RequestParam(value="pagesize",required=false) String pagesize,
			@RequestParam(value="nowpage",required=false) String nowpage) throws IOException{
		JSONObject rsJson = new JSONObject();
		rsJson.put("ver", ver);
		
		InputStream requestjson = request.getInputStream();
		String encoding = request.getCharacterEncoding(); 
		String locationsJSONString=IOUtils.toString(requestjson,encoding);

		JSONObject requestString=JSONObject.parseObject(locationsJSONString);
		logger.log(DEBUG, requestString);
		
		if(StringUtils.isEmpty(userid) && requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("userid"))){
				String uid=requestString.get("userid")+"";
				userid=Long.parseLong(uid);
			}
		}
		if(StringUtils.isEmpty(orderid) && requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("orderid"))){
				String oid=requestString.get("orderid")+"";
				orderid=Long.parseLong(oid);
			}
		}
		if(StringUtils.isEmpty(pagesize) && requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("pagesize"))){
				pagesize=requestString.get("pagesize")+"";
			}else{
				pagesize="15";
			}
		}else{
			pagesize="15";
		}
		if(StringUtils.isEmpty(nowpage) && requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("nowpage"))){
				nowpage=requestString.get("nowpage")+"";
			}else{
				nowpage="1";
			}
		}else{
			nowpage="1";
		}
		List list=null;
		PageCond page=new PageCond(Integer.parseInt(nowpage), Integer.parseInt(pagesize));
		if(StringUtils.isEmpty(userid) && StringUtils.isEmpty(orderid)){
			result="1";
			message="userid 与 orderid  为空请确认无误后再行调用";
			logger.log(INFO, message);
			data="";
		}else{
			Map map=new HashMap();
			if(!StringUtils.isEmpty(userid)){
				map.put("userid",userid);
			}
			if(!StringUtils.isEmpty(orderid)){
				map.put("orderid",orderid);
			}
			list=orderService.queryOrder(page,map);
			result="0";
			message="查询成功";
			logger.log(INFO, message);
			data=list;
			rsJson.put("page", page);
		}
		
		
		rsJson.put("result", result);
		rsJson.put("message", message);
		rsJson.put("data", data);
		response.setContentType("charset=utf-8");
		return rsJson;
	}
	
	@ResponseBody
	@RequestMapping("/manager")
	public JSONObject managerGoods(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="type",required=false) String type,
			@RequestParam(value="orderid",required=false) Long orderid,
			NsOrder nsorder) throws IOException{
		
		JSONObject rsJson = new JSONObject();
		rsJson.put("ver", ver);
		
		InputStream requestjson = request.getInputStream();
		String encoding = request.getCharacterEncoding(); 
		String locationsJSONString=IOUtils.toString(requestjson,encoding);

		JSONObject requestString=JSONObject.parseObject(locationsJSONString);
		logger.log(DEBUG, requestString);
		boolean go=true;
		JSONArray goodslist=null;
		int i=0;
		if(StringUtils.isEmpty(type)&&requestString!=null){				
			type=requestString.get("type")+"";
		}
		if(StringUtils.isEmpty(orderid)&&requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("orderid"))){
				orderid=Long.parseLong(requestString.get("orderid")+"");
			}
		}
		if(!StringUtils.isEmpty(type)&&requestString!=null){	
			goodslist=requestString.getJSONArray("goodslist");
		}
		if(!StringUtils.isEmpty(type)){
			if(type.equals("0")){//新增
				nsorder=(NsOrder)autoLoad(nsorder,"userid",requestString);
				nsorder=(NsOrder)autoLoad(nsorder,"total",requestString);
				nsorder=(NsOrder)autoLoad(nsorder,"counts",requestString);
				nsorder=(NsOrder)autoLoad(nsorder,"paytype",requestString);
				nsorder=(NsOrder)autoLoad(nsorder,"positionid",requestString);//地址
				nsorder=(NsOrder)autoLoad(nsorder,"invoice",requestString);//是否需要发票
				nsorder=(NsOrder)autoLoad(nsorder,"companyname",requestString);
				nsorder=(NsOrder)autoLoad(nsorder,"content",requestString);
				nsorder=(NsOrder)autoLoad(nsorder,"remark",requestString);//备注
				nsorder=(NsOrder)autoLoad(nsorder,"commisionCharge",requestString);//手续费
				nsorder=(NsOrder)autoLoad(nsorder,"cash",requestString);//现金
				nsorder=(NsOrder)autoLoad(nsorder,"accountAmount",requestString);//账户费用
				nsorder.setOrderstatus("1");
				
				if(!StringUtils.isEmpty(nsorder.getPositionid())){
					NsAddress address=addressService.queryByPrimaryKey(nsorder.getPositionid());
					if(address!=null && !StringUtils.isEmpty(address.getId())){
						nsorder.setAddress(address.getAddress());
						if(!StringUtils.isEmpty(address.getPost())){
							nsorder.setPostcode(address.getPost());
						}
						nsorder.setName(address.getName());
						if(!StringUtils.isEmpty(address.getPhonenumb())){							
							nsorder.setContactnumb(address.getPhonenumb());							
						}else{
							if(!StringUtils.isEmpty(address.getTelnumb())){							
								nsorder.setContactnumb(address.getTelnumb());							
							}else{
								result="1";
								message="操作失败 地址中联系电话不能为空";
								logger.log(INFO, message);
								data="";
								go=false;
							}
						}
					}else{
						result="1";
						message="操作失败  地址为空";
						logger.log(INFO, message);
						data="";
						go=false;
					}
				}else{
					result="1";
					message="操作失败  地址为空";
					logger.log(INFO, message);
					data="";
					go=false;
				}
				
				if(nsorder!=null && go){
					try{
						i=orderService.addObj(nsorder,goodslist);
					}catch(Exception e){
						logger.log(INFO, "数据插入失败~ 请确认传入参数后进行操作");
						i=0;
					}
				}
				
			}else if(type.equals("1")){//删除
				if(!StringUtils.isEmpty(orderid)){
					NsOrder order =orderService.queryByPrimaryKey(orderid);
					order.setOrderstatus("0");
					i=orderService.updateObj(order);
				}else{
					result="1";
					message="操作失败  该类型对应传入数据为空";
					logger.log(INFO, message);
					data="";
				}
			}else{
				result="1";
				message="操作失败  操作类型不匹配";
				logger.log(INFO, message);
				data="";
			}
		}else{
			result="1";
			message="操作失败  操作类型不能为空";
			logger.log(INFO, message);
			data="";
		}
		
		if(i > 0){
			result="0";
			message="操作成功";
			logger.log(INFO, message);
			data="";
		}else{
			result="1";
			message="操作失败 ";
			logger.log(INFO, message);
			data="";
		}
		
		rsJson.put("result", result);
		rsJson.put("message", message);
		rsJson.put("data", data);
		response.setContentType("charset=utf-8");
		return rsJson;
	}
	
	/**
	 * 交易订单确认
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/confirm")
	public JSONObject confirmBill(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		return null;
	}
	
	/***
	 * 交易订单申请
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/apply")
	public JSONObject applyBill(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="orderid",required=false) Long orderid,
			@RequestParam(value="subject",required=false) String subject,
			@RequestParam(value="body",required=false) String body,
			@RequestParam(value="price",required=false) String price,
			@RequestParam(value="type",required=false) String type) throws IOException{
		
		JSONObject rsJson = new JSONObject();
		rsJson.put("ver", ver);
		
		InputStream requestjson = request.getInputStream();
		String encoding = request.getCharacterEncoding(); 
		String locationsJSONString=IOUtils.toString(requestjson,encoding);

		JSONObject requestString=JSONObject.parseObject(locationsJSONString);
		logger.log(DEBUG, requestString);
		
		if(StringUtils.isEmpty(orderid)&&requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("orderid"))){
				orderid=Long.parseLong(requestString.get("orderid")+"");
			}
		}
		
		if(StringUtils.isEmpty(subject)&&requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("subject"))){
				subject=requestString.getString("subject");
			}
		}
		
		if(StringUtils.isEmpty(body)&&requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("body"))){
				body=requestString.getString("body");
			}
		}
		
		if(StringUtils.isEmpty(price)&&requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("price"))){
				price=requestString.getString("price");
			}
		}
		
		if(StringUtils.isEmpty(type)&&requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("type"))){
				type=requestString.getString("type");
			}
		}
		
		if(!StringUtils.isEmpty(price) && !StringUtils.isEmpty(body) && !StringUtils.isEmpty(subject)){
		
		if(!StringUtils.isEmpty(orderid)){
			Map querymap=new HashMap();
			querymap.put("orderid", orderid);
			querymap.put("status", "1");
			List<Object> order=orderService.queryObj(querymap);
			if(order!=null && order.size()>0){//该订单为待支付订单才可以被冻结
				try {
					Map resultmap=goodsService.freazesGoods(orderid);//冻结 保存冻结记录
					if(resultmap!=null && resultmap.size()>0){
						int status=Integer.parseInt(resultmap.get("status")+"");
						if(status == 0){
							//调用支付交易订单申请接口
							String tradeno=alipayUtil.getOutTradeNo();
							String exchangeorderinfo="";
							if(type.equals("alipay")){
								AppBusinessParameter abp=new AppBusinessParameter();
								abp.setBody(body);
								abp.setSubject(subject);
								abp.setTotal_amount(price);
								exchangeorderinfo=alipayHelp.getAppOrderInfo(abp);
							}else if(type.equals("weipay")){
//								weixinUtil.getOrderInfo(subject,body,price,tradeno);
							}else if(type.equals("nspay")){
								
							}else{
								result="1";
								message="生成失败~支付类型有误~";
								logger.log(INFO, message);
								data="";
							}
//							orderService.addExchangeOrder(obj);
							if(!StringUtils.isEmpty(exchangeorderinfo)){
								Map map=new HashMap();
								result="0";
								message="生成成功";
								logger.log(INFO, message);
								map.put("alipayOrderinfo", exchangeorderinfo);
								map.put("tradeno", tradeno);
								data=map;
							}else{
								result="1";
								message="生成失败~";
								logger.log(INFO, message);
								data="";
							}
						}else{
							result=status+"";
							message=resultmap.get("msg")+"";
							logger.log(INFO, message);
							data="";
						}
					}else{
						result="1";
						message="冻结失败~";
						logger.log(INFO, message);
						data="";
					}
				} catch (Exception e) {
					result="1";
					message=e.getMessage();
					logger.log(INFO, message);
					data="";
				}
				
			}else{
				result="1";
				message="操作失败  查询不到对应订单";
				logger.log(INFO, message);
				data="";
			}
		}else{
			result="1";
			message="操作失败  orderid不能为空";
			logger.log(INFO, message);
			data="";
		}
		
		}else{
			result="1";
			message="传入参数有误~ 请核对后重试";
			logger.log(INFO, message);
			data="";
		}
		rsJson.put("result", result);
		rsJson.put("message", message);
		rsJson.put("data", data);
		response.setContentType("charset=utf-8");
		return rsJson;
	}
	
}
