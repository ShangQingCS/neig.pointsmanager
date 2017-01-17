package cn.sqhl.neig.pointsmanager.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sqhl.neig.pointsmanager.mapper.NsCartMapper;
import cn.sqhl.neig.pointsmanager.po.NsCart;
import cn.sqhl.neig.pointsmanager.service.ShopCarService;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Cart;

@Service("shopCarService")
public class ShopCarServiceImpl implements ShopCarService{

	@Autowired
	NsCartMapper nsCartMapper;
	
	
	public List<Object> queryObj(Map<String, Object> map) {
		return nsCartMapper.selectMapList(map);
	}

	
	public int addObj(Object obj) {
		return nsCartMapper.insertSelective((NsCart)obj);
	}

	
	public int removeObj(Object obj) {
		return nsCartMapper.deleteByPrimaryKey(Long.parseLong(obj.toString()));
	}

	
	public int updateObj(Object obj) {
		return nsCartMapper.updateByPrimaryKeySelective((NsCart)obj);
	}

	
	public List<Cart> queryObj(PageCond page, Long userid) {
		return nsCartMapper.queryMapList(page,userid);
	}
	
	public List<Cart> selectList(Map map){
		return nsCartMapper.selectList(map);
	}

	
	@Transactional
	public int removeObjList(String list) throws Exception{
		int k=0;
			List<String> idlist=Arrays.asList(list.split(","));
			for(String goodsid:idlist){
				int i=nsCartMapper.deleteByPrimaryKey(Long.parseLong(goodsid));
				if(i>0){
					k=k+1;
					continue;
				}else{
					throw new RuntimeException("tansaction 异常 数据回滚");
				}
			}
		return k;
	}


	@Override
	public int removeByUserId(Long id, Long userid) {
		// TODO Auto-generated method stub
		return nsCartMapper.deleteByUserId(id, userid);
	}
	
}
