package cn.sqhl.neig.pointsmanager.service;

import java.util.List;
import java.util.Map;

import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Order;

public interface OrderService {

	public List<Order> queryOrder(PageCond page,Map<String, Object> obj);
	
	public List<Order> AddOrder(PageCond page,Map<String, Object> obj);
}
