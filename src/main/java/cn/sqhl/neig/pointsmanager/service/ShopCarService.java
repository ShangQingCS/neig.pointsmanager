package cn.sqhl.neig.pointsmanager.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Cart;

public interface ShopCarService extends BaseService{

	public List<Cart> queryObj(PageCond page,Long userid);
	
	public List<Cart> selectList(Map map);
	
	public int removeObjList (String list) throws Exception;
	
	public int removeByUserId(Long id,Long userid);
}
