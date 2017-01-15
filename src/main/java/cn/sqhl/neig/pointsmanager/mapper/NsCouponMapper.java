package cn.sqhl.neig.pointsmanager.mapper;

import java.util.List;

import cn.sqhl.neig.pointsmanager.po.NsCoupon;
import cn.sqhl.neig.pointsmanager.po.NsUserGrade;

public interface NsCouponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsCoupon record);

    int insertSelective(NsCoupon record);

    NsCoupon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsCoupon record);

    int updateByPrimaryKey(NsCoupon record);
    
   
}