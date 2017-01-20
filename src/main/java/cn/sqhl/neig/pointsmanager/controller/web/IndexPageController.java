package cn.sqhl.neig.pointsmanager.controller.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.service.AdEventService;
import cn.sqhl.neig.pointsmanager.service.GoodsService;
import cn.sqhl.neig.pointsmanager.utils.FormatUtils;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Advertise;
import cn.sqhl.neig.pointsmanager.vo.Goods;
import cn.sqhl.neig.pointsmanager.vo.GoodsCategory;

@Controller
@RequestMapping("/index_web")
public class IndexPageController extends basicInfo {

	private Logger logger = LogManager.getLogger(IndexPageController.class);

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private AdEventService adEventService;

	@RequestMapping("/home")
	public String LoadPage(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if((NsUser) request.getSession().getAttribute("user")!=null){	
		List<GoodsCategory> categorylist = goodsService.queryAllCategory();
		List<GoodsCategory> leve1 = new ArrayList<GoodsCategory>();
		List<GoodsCategory> leve2 = new ArrayList<GoodsCategory>();
		List<GoodsCategory> leve3 = new ArrayList<GoodsCategory>();
		for (GoodsCategory gc : categorylist) {
			if (gc.getLevel() == 1) {
				leve1.add(gc);
			} else if (gc.getLevel() == 2) {
				leve2.add(gc);
			} else if (gc.getLevel() == 3) {
				leve3.add(gc);
			}
		}

		// 获取所有的活动分类
		Map map = new HashMap();
		map.put("type", adtype);
		List diction = adEventService.queryDictionary(map);

		// 活动
		PageCond page = new PageCond(0, 100);
		Map mapad = new HashMap();
		mapad.put("type", "1");
		List<Advertise> adheadlist = adEventService.queryAD(page, mapad);
		mapad.put("type", "2");
		List<Advertise> adotherlist = adEventService.queryAD(page, mapad);

		model.addAttribute("leve1", leve1);
		model.addAttribute("leve2", leve2);
		model.addAttribute("leve3", leve3);
		model.addAttribute("adkind", diction);
		model.addAttribute("headlist", adheadlist);
		model.addAttribute("otherlist", adotherlist);
		model.addAttribute("baseimg", baseimg);

			return "/jsp/home";
		}else{
			return "/login";
		}
	}

	@ResponseBody
	@RequestMapping("/getcategoryall")
	public JSONObject getGoodsCategory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String result = "";
		String message = "";
		Object data = null;
		Map map = new HashMap();
		JSONObject rsJson = new JSONObject();
		// 所有分类、

		List<GoodsCategory> categorylist = goodsService.queryAllCategory();
		
		List tree=new ArrayList();
		
		if(categorylist!=null && categorylist.size()>0){
			for (GoodsCategory gc : categorylist) {
				Map leve1map=new HashMap();
				List leve2=new ArrayList();
				if (gc.getLevel() == 1) {
					leve1map.put("leve1", gc);
					for (GoodsCategory gcc : categorylist) {
						Map leve2map=new HashMap();
						List leve3=new ArrayList();
						if (gcc.getParentId() == gc.getId()) {
							leve2map.put("leve2", gcc);
							for (GoodsCategory gccc : categorylist) {
								Map leve3map=new HashMap();
								if (gccc.getParentId() == gcc.getId()) {
									leve3map.put("leve3", gccc);
									leve3.add(leve3map);
								}
								
							}
							leve2map.put("item", leve3);
							leve2.add(leve2map);
						}
					}
					leve1map.put("item", leve2);
					tree.add(leve1map);
				} 
				
			}
			map.put("tree", tree);
			
			result="0";
			message="查询成功~ ";
			data=map;
		}else{
			result="0";
			message="查询成功~ 无匹配数据";
			data=map;
		}
		rsJson.put("result", result);
		rsJson.put("message", message);
		rsJson.put("data", data);
		response.setContentType("charset=utf-8");
		return rsJson;
	}

	@RequestMapping("/search")
	public String search(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@RequestParam(value = "index_none_header_sysc", required = true) String searchcode) {

	if((NsUser) request.getSession().getAttribute("user")!=null){
		model.addAttribute("index_none_header_sysc", searchcode);
		model.addAttribute("baseimg", baseimg);
		return "/jsp/goods/search";
	}else{
		return "/login";
	}

	}
	@RequestMapping("/{parentid}/catagroygoodslist")
	public String catagroyGoodsList(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@PathVariable String parentid) {
		if((NsUser) request.getSession().getAttribute("user")!=null){
		model.addAttribute("parentid", parentid);
		model.addAttribute("baseimg", baseimg);
		return "/jsp/goods/goodslist";
		}else{
			return "/login";
		}

	}
	
	@RequestMapping("/{eventsid}/eventsgoodslist")
	public String eventsGoodsList(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@PathVariable String eventsid) {

	if((NsUser) request.getSession().getAttribute("user")!=null){	
		model.addAttribute("eventsid", eventsid);
		model.addAttribute("baseimg", baseimg);
		return "/jsp/goods/adlist";
	}else{
		return "/login";
	}
	}
}
