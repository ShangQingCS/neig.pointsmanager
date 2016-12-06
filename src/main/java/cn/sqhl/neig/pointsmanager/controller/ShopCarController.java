package cn.sqhl.neig.pointsmanager.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.sqhl.neig.pointsmanager.po.NsCart;
import cn.sqhl.neig.pointsmanager.service.ShopCarService;
import cn.sqhl.neig.pointsmanager.utils.PageCond;


@Controller
@RequestMapping("/shopcar")
public class ShopCarController extends ContextInfo{
	private Logger logger=LogManager.getLogger(ShopCarController.class);
	
	@Autowired
	private ShopCarService shopCarService;
	
	@ResponseBody
	@RequestMapping("/search")
	public JSONObject queryShopCar(HttpServletRequest request,
			HttpServletResponse response,
			@Param(value="userid") Long userid,
			@Param(value="pagesize") String pagesize,
			@Param(value="nowpage") String nowpage) throws IOException{
		JSONObject rsJson = new JSONObject();
		rsJson.put("ver", ver);
		
		InputStream requestjson = request.getInputStream();
		String encoding = request.getCharacterEncoding(); 
		String locationsJSONString=IOUtils.toString(requestjson,encoding);

		JSONObject requestString=JSONObject.parseObject(locationsJSONString);
		logger.log(DEBUG, requestString);
		
		if(StringUtils.isEmpty(userid) && requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("userid"))){
				String usid=requestString.get("userid")+"";
				if(!StringUtils.isEmpty(usid)){
					userid=Long.parseLong(usid);
				}
			}else{
				result="1";
				message="userid 为空请确认无误后再行调用";
				logger.log(INFO, message);
				data="";
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
				nowpage="0";
			}
		}else{
			nowpage="0";
		}
		List list=null;
		PageCond page=new PageCond(Integer.parseInt(nowpage)*Integer.parseInt(pagesize),Integer.parseInt(pagesize));
		if(!StringUtils.isEmpty(userid)){
			list=shopCarService.queryObj(page,userid);
			
				result="0";
				message="查询成功~";
				logger.log(INFO, message);
				data=list;
				rsJson.put("page", page);
			
		}else{
			result="1";
			message="userid 为空请确认无误后再行调用";
			logger.log(INFO, message);
			data="";
		}
		rsJson.put("result", result);
		rsJson.put("message", message);
		rsJson.put("data", data);
		response.setContentType("charset=utf-8");
		return rsJson;
	}
	
	@ResponseBody
	@RequestMapping(value="/manager")
	public JSONObject managerAddress(HttpServletRequest request,
			HttpServletResponse response,
			NsCart cart,
			@Param("type") String type) throws IOException{
		JSONObject rsJson = new JSONObject();
		rsJson.put("ver", ver);
		InputStream requestjson = request.getInputStream();
		String encoding = request.getCharacterEncoding(); 
		String locationsJSONString=IOUtils.toString(requestjson,encoding);

		JSONObject requestString=JSONObject.parseObject(locationsJSONString);
		logger.log(DEBUG, requestString);
		
		if(StringUtils.isEmpty(type)&&requestString!=null){				
			type=requestString.get("type")+"";
		}
		cart=(NsCart)autoLoad(cart,"userid",requestString);
		cart=(NsCart)autoLoad(cart,"goodsid",requestString);
		cart=(NsCart)autoLoad(cart,"count",requestString);
		cart=(NsCart)autoLoad(cart,"id",requestString);
		
		int i=0;
		if(!StringUtils.isEmpty(type)){// 1 删除 2修改 0新增
			if(!StringUtils.isEmpty(cart.getId())){ 
				if(type.equals("1")){
					i=shopCarService.removeObj(cart.getId());
					
				}else if(type.equals("2")){
					i=shopCarService.updateObj(cart);
					
				}else{
					result="1";
					message="type 操作类型有误";
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
					message="操作失败";
					logger.log(INFO, message);
					data="";
				}
				
			}else{
				if(type.equals("0")){
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("userid", cart.getUserid());
					map.put("goodsid", cart.getGoodsid());
					List clist=shopCarService.queryObj(map);
					if(clist.size()>0){
						int size=cart.getCount();
						cart=(NsCart)clist.get(0);
						cart.setCount(size+cart.getCount());
						i=shopCarService.updateObj(cart);
					}else{
						i=shopCarService.addObj(cart);						
					}
					
					if(i > 0){
						result="0";
						message="操作成功";
						logger.log(INFO, message);
						data="";
					}else{
						result="1";
						message="操作失败";
						logger.log(INFO, message);
						data="";
					}
				}else{
					result="1";
					message="该type操作类型 id不能为空";
					logger.log(INFO, message);
					data="";
				}
			}
		}else{
			result="1";
			message="type 操作类型不能为空";
			logger.log(INFO, message);
			data="";
		}
		
		rsJson.put("result", result);
		rsJson.put("message", message);
		rsJson.put("data", data);
		return rsJson;
	}
}
