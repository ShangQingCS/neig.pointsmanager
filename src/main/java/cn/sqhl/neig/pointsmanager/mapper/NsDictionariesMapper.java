package cn.sqhl.neig.pointsmanager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.sqhl.neig.pointsmanager.po.NsDictionaries;
import cn.sqhl.neig.pointsmanager.vo.Dictionaries;

public interface NsDictionariesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NsDictionaries record);

    int insertSelective(NsDictionaries record);

    NsDictionaries selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NsDictionaries record);

    int updateByPrimaryKey(NsDictionaries record);
    
    List<Dictionaries> selectDictionaries(@Param("map") Map<String, Object> map);
}