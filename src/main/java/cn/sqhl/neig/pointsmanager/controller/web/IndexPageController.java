package cn.sqhl.neig.pointsmanager.controller.web;

import java.util.List;

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


@Controller
@RequestMapping("/index")
public class IndexPageController {

	private Logger logger=LogManager.getLogger(IndexPageController.class);
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private AdEventService adEventService;
	
	@RequestMapping("/home")
	public String LoadPage(HttpServletRequest request,HttpServletResponse response,Model model){
		//所有分类、
		
		List categorylist=goodsService.queryAllCategory();
		
		//
		List 
		
		//活动
		PageCond page=new PageCond();
		page.setLength(100);
		page.setCurrentPage(0);
		List<Advertise> adlist=adEventService.queryAD(page,null);
		for(Advertise ad:adlist){
			ad.getKind()
		}
		List headerlist=null;
		List otherlist=null;
		
		
		return "/jsp/home";
	}
}
