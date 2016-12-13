package cn.sqhl.neig.pointsmanager.controller.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cn.sqhl.neig.pointsmanager.service.ShopCarService;
import cn.sqhl.neig.pointsmanager.vo.web.ErrorInfo;


@Controller
@RequestMapping("/shopcar_web")
public class ShopWebCarController extends basicInfo{
	private Logger logger=LogManager.getLogger(ShopWebCarController.class);
	
	@Autowired
	private ShopCarService shopCarService;
	
	
	@RequestMapping("/search")
	public String queryShopCar(HttpServletRequest request,
			HttpServletResponse response,Model model,
			@RequestParam(value="userid",required=false) Long userid) throws IOException{
		ErrorInfo einfo;
		List list;
		/*
		 * for test
		 */
		userid=Long.parseLong("4002");
		
		if(StringUtils.isNotBlank(userid+"")){
			Map map=new HashMap();
			map.put("userid", userid);
			list=shopCarService.selectList(map);
			model.addAttribute("baseimg", baseimg);
			model.addAttribute("cartlist", list);
			model.addAttribute("userid", userid);
			return "/jsp/order/shopcart";
		}else{
			einfo=new ErrorInfo();
			einfo.setResult("1");
			einfo.setMessage("查询失败");
			einfo.setCause("请先登录后 在进行查看~");
			model.addAttribute("result", einfo);
			return "/login";
		}
		
	}
}
