package cn.sqhl.neig.pointsmanager.controller.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sqhl.neig.pointsmanager.service.AdEventService;
import cn.sqhl.neig.pointsmanager.service.GoodsService;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Advertise;
import cn.sqhl.neig.pointsmanager.vo.GoodsCategory;


@Controller
@RequestMapping("/index_web")
public class IndexPageController extends basicInfo{

	private Logger logger=LogManager.getLogger(IndexPageController.class);
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private AdEventService adEventService;
	
	@RequestMapping("/home")
	public String LoadPage(HttpServletRequest request,HttpServletResponse response,Model model){
		//所有分类、
		
		List<GoodsCategory> categorylist=goodsService.queryAllCategory();
		List<GoodsCategory> leve1=new ArrayList<GoodsCategory>();
		List<GoodsCategory> leve2=new ArrayList<GoodsCategory>();
		List<GoodsCategory> leve3=new ArrayList<GoodsCategory>();
		for(GoodsCategory gc:categorylist){
			if(gc.getLevel()==1){
				leve1.add(gc);
			}else if(gc.getLevel()==2){
				leve2.add(gc);
			}else if(gc.getLevel()==3){
				leve3.add(gc);
			}
		}
		
		//获取所有的活动分类
		Map map=new HashMap();
		map.put("type",adtype);
		List diction =adEventService.queryDictionary(map);
		
		
		//活动
		PageCond page=new PageCond();
		page.setLength(100);
		page.setCurrentPage(0);
		Map mapad=new HashMap();
		mapad.put("type","1");
		List<Advertise> adheadlist=adEventService.queryAD(page,mapad);
		mapad.put("type","2");
		List<Advertise> adotherlist=adEventService.queryAD(page,mapad);
		
		model.addAttribute("leve1", leve1);
		model.addAttribute("leve2", leve2);
		model.addAttribute("leve3", leve3);
		model.addAttribute("adkind", diction);
		model.addAttribute("headlist", adheadlist);
		model.addAttribute("otherlist", adotherlist);
		
		return "/jsp/home";
	}
}
