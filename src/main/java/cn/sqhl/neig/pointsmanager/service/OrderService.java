package cn.sqhl.neig.pointsmanager.service;

import java.util.List;
import java.util.Map;

import cn.sqhl.neig.pointsmanager.po.NsOrder;
import cn.sqhl.neig.pointsmanager.po.NsOrderDetail;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Order;

public interface OrderService extends BaseService{

	public List<Order> queryOrder(PageCond page,Map<String, Object> obj);
	
	public int addObj(NsOrder order,Object goodslist) throws Exception;
	
	public NsOrder queryByPrimaryKey(Long id);
	
	public int insertOrderDetail(NsOrderDetail record);
}
