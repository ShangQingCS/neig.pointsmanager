package cn.sqhl.neig.pointsmanager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sqhl.neig.pointsmanager.mapper.NsGoodsMapper;
import cn.sqhl.neig.pointsmanager.po.NsGoods;
import cn.sqhl.neig.pointsmanager.service.BaseService;
import cn.sqhl.neig.pointsmanager.service.GoodsService;
import cn.sqhl.neig.pointsmanager.vo.Goods;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService,BaseService{

	@Autowired
	NsGoodsMapper nsGoodsMapper;
	
	@Override
	public List<Object> queryObj(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Goods queryObj(Long id) {
		NsGoods nsgoods =nsGoodsMapper.selectByPrimaryKey(id);
		return new Goods(nsgoods.getId(),nsgoods.getGname(),nsgoods.getPrice(),nsgoods.getBrand(), nsgoods.getGoodimglist(), nsgoods.getGfullname(), nsgoods.getSellnumb(), nsgoods.getGoodimg(), nsgoods.getDetail());
	}
}
