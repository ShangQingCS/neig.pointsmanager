package cn.sqhl.neig.pointsmanager.mapper;

import cn.sqhl.neig.pointsmanager.po.NsFreazesLog;

public interface NsFreazesLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NsFreazesLog record);

    int insertSelective(NsFreazesLog record);

    NsFreazesLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NsFreazesLog record);

    int updateByPrimaryKey(NsFreazesLog record);
}