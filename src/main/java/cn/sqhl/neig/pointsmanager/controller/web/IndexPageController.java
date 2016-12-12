package cn.sqhl.neig.pointsmanager.controller.web;

import java.util.ArrayList;
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
		// 所有分类、

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
		PageCond page = new PageCond(0,100);
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
	}

	@RequestMapping("/search")
	public String search(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@RequestParam(value = "index_none_header_sysc", required = true) String searchcode,
			@RequestParam(value = "currentPage", required = false) String currentPage,
			@RequestParam(value = "length", required = false) String length) {

		List<Goods> list = null;
		PageCond page = null;
		Map querymap = new HashMap();
		if (StringUtils.isEmpty(currentPage)) {
			currentPage = "0";
		}
		if (StringUtils.isEmpty(length)) {
			length = "20";
		}
		page = new PageCond(Integer.parseInt(length)
				* Integer.parseInt(currentPage), Integer.parseInt(length));

		if (StringUtils.isNotBlank(searchcode)) {
			querymap.put("searchcode", searchcode);
			list = goodsService.queryPageByLike(page, querymap);
		}
		Map map = new HashMap();
		map.put("currentPage", currentPage);
		map.put("length", length);
		map.put("index_none_header_sysc", searchcode);
		if (list != null && list.size() > 0) {
			model.addAttribute("list", list);
			model.addAttribute("page", page);
			model.addAttribute("url", request.getRequestURL() + "?"
					+ FormatUtils.MaptoStringforUrl(map));
			model.addAttribute("baseimg", baseimg);
		}
		return "/jsp/search";

	}
}
