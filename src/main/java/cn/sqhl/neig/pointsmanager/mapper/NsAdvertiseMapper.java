package cn.sqhl.neig.pointsmanager.mapper;

import cn.sqhl.neig.pointsmanager.po.NsAdvertise;

public interface NsAdvertiseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsAdvertise record);

    int insertSelective(NsAdvertise record);

    NsAdvertise selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsAdvertise record);

    int updateByPrimaryKeyWithBLOBs(NsAdvertise record);

    int updateByPrimaryKey(NsAdvertise record);
}