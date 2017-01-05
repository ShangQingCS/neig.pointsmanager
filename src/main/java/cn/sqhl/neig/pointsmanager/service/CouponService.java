package cn.sqhl.neig.pointsmanager.service;

import java.util.List;

import cn.sqhl.neig.pointsmanager.po.NsUserCoupon;




public interface CouponService extends BaseService{
	public List<NsUserCoupon>selectByUserId(Long userId,String status);
	
	
	
}
