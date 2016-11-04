package cn.sqhl.neig.pointsmanager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sqhl.neig.pointsmanager.mapper.NsCartMapper;
import cn.sqhl.neig.pointsmanager.po.NsCart;
import cn.sqhl.neig.pointsmanager.service.ShopCarService;
import cn.sqhl.neig.pointsmanager.utils.PageCond;

@Service("shopCarService")
public class ShopCarServiceImpl implements ShopCarService{

	@Autowired
	NsCartMapper nsCartMapper;
	
	@Override
	public List<Object> queryObj(Map<String, Object> map) {
		return nsCartMapper.selectMapListByUserid(map);
	}

	@Override
	public int addObj(Object obj) {
		nsCartMapper.insertSelective((NsCart)obj);
		return 0;
	}

	@Override
	public int removeObj(Object obj) {
		nsCartMapper.deleteByPrimaryKey(Long.parseLong(obj.toString()));
		return 0;
	}

	@Override
	public int updateObj(Object obj) {
		nsCartMapper.updateByPrimaryKeySelective((NsCart)obj);
		return 0;
	}

	@Override
	public List<Object> queryObj(PageCond page, Long userid) {
		nsCartMapper.queryMapList(page,userid);
		return null;
	}
	
}
