package cn.sqhl.neig.pointsmanager.service;

import java.util.List;
import java.util.Map;

import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Goods;
import cn.sqhl.neig.pointsmanager.vo.GoodsCategory;

public interface GoodsService {
	public Goods queryObj(Long id);
	public List<Goods> queryPageByParentID(PageCond page,Object obj);
	public List<Goods> queryPageByLike(PageCond page,Object obj);
	public List<GoodsCategory> queryGoodsCategory(PageCond page,Object obj);
}
