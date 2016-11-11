package cn.sqhl.neig.pointsmanager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sqhl.neig.pointsmanager.mapper.NsOrderMapper;
import cn.sqhl.neig.pointsmanager.service.OrderService;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Order;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Autowired
	private NsOrderMapper nsOrderMapper;
	
	@Override
	public List<Order> queryOrder(PageCond page, Map<String, Object> obj) {
		return nsOrderMapper.queryOrder(page,obj);
	}

	@Transactional
	public List<Order> AddOrder(PageCond page, Map<String, Object> obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
