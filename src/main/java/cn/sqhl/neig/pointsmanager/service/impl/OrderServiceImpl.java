package cn.sqhl.neig.pointsmanager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;

import cn.sqhl.neig.pointsmanager.mapper.NsOrderDetailMapper;
import cn.sqhl.neig.pointsmanager.mapper.NsOrderMapper;
import cn.sqhl.neig.pointsmanager.mapper.NsUserCouponMapper;
import cn.sqhl.neig.pointsmanager.po.NsOrder;
import cn.sqhl.neig.pointsmanager.po.NsOrderDetail;
import cn.sqhl.neig.pointsmanager.po.NsUserCoupon;
import cn.sqhl.neig.pointsmanager.service.OrderService;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Order;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Autowired
	private NsOrderMapper nsOrderMapper;
	
	@Autowired
	private NsOrderDetailMapper nsOrderDetailMapper;
	
	@Autowired
	private NsUserCouponMapper nsUserCouponMapper;
	
	
	public List<Order> queryOrder(PageCond page, Map<String, Object> obj) {
		return nsOrderMapper.queryOrder(page,obj);
	}
	
	
	public List<Object> queryObj(Map<String, Object> map) {
		return nsOrderMapper.selectOrder(map);
	}

	
	public int addObj(Object obj) {
		return nsOrderMapper.insertSelective((NsOrder)obj);
	}

	
	public int removeObj(Object obj) {
		return nsOrderMapper.deleteByPrimaryKey(Long.parseLong(obj.toString()));
	}

	
	public int updateObj(Object obj) {
		return nsOrderMapper.updateByPrimaryKeySelective((NsOrder)obj);
	}

	@Transactional
	public NsOrder addObj(NsOrder order,Object goodslist,String couponid,String userid) throws Exception{
		 int value=0;
		 int z=nsOrderMapper.insertOrder(order);
			 if(z > 0 && !StringUtils.isEmpty(order.getId())){
				 JSONArray obj=(JSONArray)goodslist;
				 for(int i=0;i<obj.size();i++){				 
					 NsOrderDetail odtail=obj.getObject(i,NsOrderDetail.class);
					 odtail.setOrderid(order.getId());
					 int k=nsOrderDetailMapper.insertSelective(odtail);
					 if(k>0){
						continue;
					 }else{
						order=null;
						throw new RuntimeException("tansaction 异常 数据回滚");
					 }
				 }
				 if(!StringUtils.isEmpty(couponid)){
					 Map map=new HashMap();
					 map.put("id", couponid);
					 map.put("userid", userid);
					 NsUserCoupon nucoupon =nsUserCouponMapper.selectbyMap(map);
					 if(nucoupon!=null){
					 int k=0;
					 if(nucoupon.getCouponStatus().equals("0")){
						 nucoupon.setCouponStatus("1");
						 nucoupon.setOrderSn(order.getId()+"");
						 k=nsUserCouponMapper.updateByPrimaryKeySelective(nucoupon);
					 }
					 if(k>0){
						 value=1;
					 }else{
						order=null;
						throw new RuntimeException("tansaction 优惠券不可使用");
					 }}
					 else{
						order=null;
						throw new RuntimeException("tansaction 无对应优惠券");
					 }
				 }
			 }else{
				 order=null;
			 }
			return order;
	}
	
	public NsOrder queryByPrimaryKey(Long id){
		return nsOrderMapper.selectByPrimaryKey(id);
	}


	@Override
	public int insertOrderDetail(NsOrderDetail record) {
		// TODO Auto-generated method stub
		return nsOrderDetailMapper.insert(record);
	}
}
