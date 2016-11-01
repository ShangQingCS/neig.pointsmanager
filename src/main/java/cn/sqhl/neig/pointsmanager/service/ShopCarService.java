package cn.sqhl.neig.pointsmanager.service;

import java.util.List;
import java.util.Map;

import cn.sqhl.neig.pointsmanager.utils.PageCond;

public interface ShopCarService extends BaseService{

	public List<Object> queryObj(PageCond page,Long userid);
}
