package cn.sqhl.neig.pointsmanager.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;

import cn.sqhl.neig.pointsmanager.po.NsAddress;
import cn.sqhl.neig.pointsmanager.po.NsCart;
import cn.sqhl.neig.pointsmanager.service.ShopCarService;
import cn.sqhl.neig.pointsmanager.utils.FormatUtils;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Goods;


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
		}
		if(StringUtils.isEmpty(nowpage) && requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("nowpage"))){
				nowpage=requestString.get("nowpage")+"";
			}else{
				nowpage="1";
			}
		}
		List list=null;
		PageCond page=new PageCond(Integer.parseInt(nowpage), Integer.parseInt(pagesize));
		if(!StringUtils.isEmpty(userid.toString())){
			Map map=new HashMap();
			map.put("userid",userid);
			list=shopCarService.queryObj(page,userid);
		}else{
			result="1";
			message="parentid 为空请确认无误后再行调用";
			logger.log(INFO, message);
			data="";
		}
		if(list!=null && list.size() > 0 ){
			result="0";
			message="查询成功~";
			logger.log(INFO, message);
			data=list;
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
			List<NsCart> cartlist,
			@Param(value="userid") Long userid,
			@Param("type") String type) throws IOException{
		JSONObject rsJson = new JSONObject();
		rsJson.put("ver", ver);
		InputStream requestjson = request.getInputStream();
		String encoding = request.getCharacterEncoding(); 
		String locationsJSONString=IOUtils.toString(requestjson,encoding);

		JSONObject requestString=JSONObject.parseObject(locationsJSONString);
		logger.log(DEBUG, requestString);
		try {
			if(StringUtils.isEmpty(type)&&requestString!=null){				
				type=requestString.get("type")+"";
			}
			if(StringUtils.isEmpty(userid)){				
				if(requestString!=null){
					String usersid=requestString.get("userid")+"";
					if(!StringUtils.isEmpty(usersid)){
						userid=Long.parseLong(usersid);
					}
				}else{
					String usersid=request.getParameter("userid");
					if(!StringUtils.isEmpty(usersid)){
						userid=Long.parseLong(usersid);
					}
				}
			}
			
			List<JSONObject> goodslist=(List<JSONObject>)requestString.get("goodslist");
			
			List<NsCart> nsCartlist=null;
			if(goodslist!=null && goodslist.size()>0){
				nsCartlist=new ArrayList<NsCart>();
				for(JSONObject json:goodslist){
					NsCart cart=TypeUtils.castToJavaBean(json,NsCart.class);
					if(cart!=null){
						if(!StringUtils.isEmpty(userid)){
							cart.setUserid(userid);
							nsCartlist.add(cart);
						}else{
							result="1";
							message="usersid 为空 请确认信息后提交";
							logger.log(INFO, message);
							data="";
							break;
						}
					}
				}
			}else{
				result="1";
				message="goodslist 为空 请确认信息后提交";
				logger.log(INFO, message);
				data="";
			}
			
			if(nsCartlist!=null && nsCartlist.size()>0){
				for(NsCart cart:nsCartlist){
					if(!StringUtils.isEmpty(cart.getUserid())){
						if(!StringUtils.isEmpty(type)){// 1 删除 2修改 0新增
							if(!StringUtils.isEmpty(cart.getId())){ 
								int i=0;
								if(type.equals("1")){
									i=shopCarService.removeObj(cart);
									
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
									Map map=new HashMap();
									map.put("userid", userid);
									List<Object> cartls=shopCarService.queryObj(map);
									boolean tr=false;
									int numb=0;
									for(int k=0;k< cartls.size();k++){
										NsCart carts=(NsCart)cartls.get(k);
										if(carts.getGoodsid().equals(cart.getGoodsid())){
											tr=true;
											numb=cart.getCount();
											cart=carts;
										}
									}
									int i=0;
									if(!tr && numb==0){
										i=shopCarService.addObj(cart);
									}else{
										cart.setCount(cart.getCount()+numb);
										i=shopCarService.updateObj(cart);
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
									message="该type操作类型 userid不能为空";
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
					}else{
						result="1";
						message="userid不能为空";
						logger.log(INFO, message);
						data="";
					}
				}
			}else{
				result="1";
				message="goodslist 为空 请确认信息后提交";
				logger.log(INFO, message);
				data="";
			}
			
			

		    
		} catch (Exception e) {
			result="1";
			message=e.getMessage();
			logger.log(ERROR,e.toString());
			data=null;
		}
		rsJson.put("result", result);
		rsJson.put("message", message);
		rsJson.put("data", data);
		return rsJson;
	}
}
