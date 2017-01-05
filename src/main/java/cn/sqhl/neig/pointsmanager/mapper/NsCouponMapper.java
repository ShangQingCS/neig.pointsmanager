package cn.sqhl.neig.pointsmanager.mapper;

import cn.sqhl.neig.pointsmanager.po.NsCoupon;

public interface NsCouponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsCoupon record);

    int insertSelective(NsCoupon record);

    NsCoupon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsCoupon record);

    int updateByPrimaryKey(NsCoupon record);
}