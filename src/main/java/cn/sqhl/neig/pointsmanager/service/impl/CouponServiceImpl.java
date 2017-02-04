package cn.sqhl.neig.pointsmanager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sqhl.neig.pointsmanager.mapper.NsUserCouponMapper;
import cn.sqhl.neig.pointsmanager.mapper.NsUserGradeMapper;
import cn.sqhl.neig.pointsmanager.po.NsUserCoupon;
import cn.sqhl.neig.pointsmanager.po.NsUserGrade;
import cn.sqhl.neig.pointsmanager.service.CouponService;

@Service("couponService")
public class CouponServiceImpl implements CouponService{
	@Autowired
	private NsUserCouponMapper nsUserCouponMapper;
	@Autowired
	private NsUserGradeMapper nsUserGradeMapper ;
	
	public List<Object> queryObj(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int addObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int removeObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public List<NsUserCoupon> selectByUserId(Long userId, String status) {
		// TODO Auto-generated method stub
		return nsUserCouponMapper.selectByUserId(userId,status);
	}

	
	public List<NsUserGrade> selectUserGrade() {
		// TODO Auto-generated method stub
		return nsUserGradeMapper.selectUserGrade();
	}

	
	public NsUserGrade selectUserGradebyID(long userGradeId) {
		// TODO Auto-generated method stub
		return nsUserGradeMapper.selectByPrimaryKey(userGradeId);

	}

	@Override
	public int deleteObj(Long id) {
		// TODO 自动生成的方法存根
		return nsUserCouponMapper.deleteByPrimaryKey(id);
	}


	

	

}
