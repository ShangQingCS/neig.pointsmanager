package cn.sqhl.neig.pointsmanager.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.service.GoodsService;
import cn.sqhl.neig.pointsmanager.vo.Goods;
import cn.sqhl.neig.pointsmanager.vo.web.ErrorInfo;


@Controller
@RequestMapping("/goods_web")
public class GoodsWebController extends basicInfo{
	private Logger logger=LogManager.getLogger(GoodsWebController.class);
	
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping("/goods/{goodsid}/search")
	public String Login(HttpServletRequest request,HttpServletResponse response,Model model,
			@PathVariable String goodsid){
		
		Goods goods=goodsService.queryObj(Long.parseLong(goodsid));
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		if(user!=null && goods!=null){
			model.addAttribute("goods", goods);
			model.addAttribute("baseimg", baseimg);
			
			model.addAttribute("userid", user.getId());
			return "/jsp/goods/introduction";
		}else{
			ErrorInfo ef=new ErrorInfo();
			ef.setResult("1");
			ef.setMessage("查询失败");
			ef.setCause("无对应商品~");
			model.addAttribute("result", ef);
			return "/login";
		}
	}
	
	@RequestMapping("/events/{eventsid}/search")
	public String eventSearch(HttpServletRequest request,HttpServletResponse response,Model model,
			@PathVariable String eventsid){
		
		model.addAttribute("eventsid", eventsid);
		model.addAttribute("baseimg", baseimg);
		return "/jsp/goods/adlist";
	}
}
