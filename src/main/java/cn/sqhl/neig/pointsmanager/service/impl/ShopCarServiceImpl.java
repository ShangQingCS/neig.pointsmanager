package cn.sqhl.neig.pointsmanager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sqhl.neig.pointsmanager.mapper.NsCartMapper;
import cn.sqhl.neig.pointsmanager.po.NsCart;
import cn.sqhl.neig.pointsmanager.service.ShopCarService;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Cart;

@Service("shopCarService")
public class ShopCarServiceImpl implements ShopCarService{

	@Autowired
	NsCartMapper nsCartMapper;
	
	@Override
	public List<Object> queryObj(Map<String, Object> map) {
		return nsCartMapper.selectMapList(map);
	}

	@Override
	public int addObj(Object obj) {
		return nsCartMapper.insertSelective((NsCart)obj);
	}

	@Override
	public int removeObj(Object obj) {
		return nsCartMapper.deleteByPrimaryKey(Long.parseLong(obj.toString()));
	}

	@Override
	public int updateObj(Object obj) {
		return nsCartMapper.updateByPrimaryKeySelective((NsCart)obj);
	}

	@Override
	public List<Cart> queryObj(PageCond page, Long userid) {
		return nsCartMapper.queryMapList(page,userid);
	}
	
}
