package cn.sqhl.neig.pointsmanager.service;

import java.util.List;
import java.util.Map;

import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Cart;

public interface ShopCarService extends BaseService{

	public List<Cart> queryObj(PageCond page,Long userid);
}
