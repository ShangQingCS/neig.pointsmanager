package cn.sqhl.neig.pointsmanager.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.sqhl.neig.pointsmanager.service.GoodsService;
import cn.sqhl.neig.pointsmanager.utils.FormatUtils;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Goods;


@Controller
@RequestMapping("/goods")
public class GoodsController extends ContextInfo{
	@Autowired
	private GoodsService goodsService;
	
	private Logger logger=LogManager.getLogger(GoodsController.class);
	
	@ResponseBody
	@RequestMapping("/search")
	public JSONObject queryGoods(HttpServletRequest request,HttpServletResponse response,@Param(value="goodsid") Long id) throws IOException{
		JSONObject rsJson = new JSONObject();
		rsJson.put("ver", ver);
		
		InputStream requestjson = request.getInputStream();
		String locationsJSONString=FormatUtils.getStringFromHttpforCN(requestjson);

		JSONObject requestString=JSONObject.parseObject(locationsJSONString);
		logger.log(DEBUG, requestString);
		
		if(StringUtils.isEmpty(id.toString())){
			if(requestString!=null){
				String goodsid=requestString.get("goodsid")+"";
				if(!StringUtils.isEmpty(goodsid)){
					id=Long.parseLong(goodsid);
				}
			}else{
				result="1";
				message="goodsid 为空请确认无误后再行调用";
				logger.log(INFO, message);
				data="";
			}
		}
		Goods goods=null;
		if(!StringUtils.isEmpty(id.toString())){
			goods=goodsService.queryObj(id);
			List list=new ArrayList();
			if(goods!=null){
				list.add(goods);
				result="0";
				message="查询成功";
				logger.log(INFO, message);
				data=list;
			}else{
				result="0";
				message="查询成功~ 无对应商品";
				logger.log(INFO, message);
				data=list;
			}
		}else{
			result="1";
			message="goodsid 为空请确认无误后再行调用";
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
	@RequestMapping("/searchlist")
	public JSONObject queryGoodsList(HttpServletRequest request,HttpServletResponse response,
			@Param(value="parentid") String parentid,
			@Param(value="searchcode") String searchcode,
			@Param(value="pagesize") String pagesize,
			@Param(value="nowpage") String nowpage) throws IOException{
		JSONObject rsJson = new JSONObject();
		rsJson.put("ver", ver);
		
		InputStream requestjson = request.getInputStream();
		String locationsJSONString=FormatUtils.getStringFromHttpforCN(requestjson);
		JSONObject requestString=JSONObject.parseObject(locationsJSONString);
		logger.log(DEBUG, requestString);
		if(StringUtils.isEmpty(parentid)){
			if(requestString!=null){
				parentid=requestString.get("parentid")+"";
			}
		}
		if(StringUtils.isEmpty(searchcode)){
			if(requestString!=null){
				searchcode=requestString.get("searchcode")+"";
			}
		}
		if(StringUtils.isEmpty(pagesize)){
			if(requestString!=null){
				pagesize=requestString.get("pagesize")+"";
			}else{
				pagesize="15";
			}
		}
		if(StringUtils.isEmpty(nowpage)){
			if(requestString!=null){
				nowpage=requestString.get("nowpage")+"";
			}else{
				nowpage="1";
			}
		}
		List list=null;
		PageCond page=new PageCond(Integer.parseInt(nowpage), Integer.parseInt(pagesize));
		if(StringUtils.isEmpty(parentid) && StringUtils.isEmpty(searchcode)){
			result="1";
			message="parentid 与 searchcode  为空请确认无误后再行调用";
			logger.log(INFO, message);
			data="";
		}else{
			if(!StringUtils.isEmpty(parentid)){
				Map map=new HashMap();
				map.put("parentid",parentid);
				list=goodsService.queryPageByParentID(page,map);
			}
			if(!StringUtils.isEmpty(searchcode)){
				Map map=new HashMap();
				map.put("searchcode",searchcode);
				list=goodsService.queryPageByLike(page,map);
			}
		}
		if(list!=null && list.size() > 0 ){
			data=list;
		}
		rsJson.put("result", result);
		rsJson.put("message", message);
		rsJson.put("data", data);
		response.setContentType("charset=utf-8");
		return rsJson;
	}
	@ResponseBody
	@RequestMapping("/searchcategory")
	public JSONObject queryGoodsCategory(HttpServletRequest request,HttpServletResponse response,
			@Param(value="parentid") String parentid,
			@Param(value="pagesize") String pagesize,
			@Param(value="nowpage") String nowpage) throws IOException{
		JSONObject rsJson = new JSONObject();
		rsJson.put("ver", ver);
		
		InputStream requestjson = request.getInputStream();
		String locationsJSONString=FormatUtils.getStringFromHttpforCN(requestjson);

		JSONObject requestString=JSONObject.parseObject(locationsJSONString);
		logger.log(DEBUG, requestString);
		if(StringUtils.isEmpty(parentid)){
			if(requestString!=null){
				parentid=requestString.get("parentid")+"";
			}
		}
		if(StringUtils.isEmpty(pagesize)){
			if(requestString!=null){
				pagesize=requestString.get("pagesize")+"";
			}else{
				pagesize="15";
			}
		}
		if(StringUtils.isEmpty(nowpage)){
			if(requestString!=null){
				nowpage=requestString.get("nowpage")+"";
			}else{
				nowpage="1";
			}
		}
		List list=null;
		PageCond page=new PageCond(Integer.parseInt(nowpage), Integer.parseInt(pagesize));
		if(!StringUtils.isEmpty(parentid)){
			Map map=new HashMap();
			map.put("parentid",parentid);
			list=goodsService.queryGoodsCategory(page,map);
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
	
}
