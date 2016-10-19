package cn.sqhl.neig.pointsmanager.mapper;

import cn.sqhl.neig.pointsmanager.po.NsOrder;

public interface NsOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsOrder record);

    int insertSelective(NsOrder record);

    NsOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsOrder record);

    int updateByPrimaryKey(NsOrder record);
}