package cn.sqhl.neig.pointsmanager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sqhl.neig.pointsmanager.mapper.NsUserCouponMapper;

import cn.sqhl.neig.pointsmanager.po.NsUserCoupon;
import cn.sqhl.neig.pointsmanager.service.CouponService;

@Service("couponService")
public class CouponServiceImpl implements CouponService{
	@Autowired
	private NsUserCouponMapper nsUserCouponMapper;
	
	
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
	public List<NsUserCoupon> selectByUserId(Long userId, String status) {
		// TODO Auto-generated method stub
		return nsUserCouponMapper.selectByUserId(userId,status);
	}

	

}
