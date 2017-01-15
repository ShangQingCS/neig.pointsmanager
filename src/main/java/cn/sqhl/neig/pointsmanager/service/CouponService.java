package cn.sqhl.neig.pointsmanager.service;

import java.util.List;

import cn.sqhl.neig.pointsmanager.po.NsUserCoupon;
import cn.sqhl.neig.pointsmanager.po.NsUserGrade;

public interface CouponService extends BaseService{
	public List<NsUserCoupon>selectByUserId(Long userId,String status);
	
	public List<NsUserGrade> selectUserGrade();
	
	public NsUserGrade selectUserGradebyID(long userGradeId);
	
	

}
