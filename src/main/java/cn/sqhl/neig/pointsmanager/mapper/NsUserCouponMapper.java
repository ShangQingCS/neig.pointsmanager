package cn.sqhl.neig.pointsmanager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sqhl.neig.pointsmanager.po.NsUserCoupon;

public interface NsUserCouponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsUserCoupon record);

    int insertSelective(NsUserCoupon record);

    NsUserCoupon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsUserCoupon record);

    int updateByPrimaryKey(NsUserCoupon record);
    
    public List<NsUserCoupon>selectByUserId(@Param(value="userId")Long userId,@Param(value="status")String status);
}