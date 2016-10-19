package cn.sqhl.neig.pointsmanager.mapper;

import cn.sqhl.neig.pointsmanager.po.NsOrderDetail;

public interface NsOrderDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsOrderDetail record);

    int insertSelective(NsOrderDetail record);

    NsOrderDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsOrderDetail record);

    int updateByPrimaryKey(NsOrderDetail record);
}