package cn.sqhl.neig.pointsmanager.mapper;

import cn.sqhl.neig.pointsmanager.po.NsDictionaries;

public interface NsDictionariesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsDictionaries record);

    int insertSelective(NsDictionaries record);

    NsDictionaries selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsDictionaries record);

    int updateByPrimaryKey(NsDictionaries record);
}