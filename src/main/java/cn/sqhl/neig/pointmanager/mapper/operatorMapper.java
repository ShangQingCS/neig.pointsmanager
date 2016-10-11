package cn.sqhl.neig.pointmanager.mapper;

import cn.sqhl.neig.pointsmanager.vo.operator;

public interface operatorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(operator record);

    int insertSelective(operator record);

    operator selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(operator record);

    int updateByPrimaryKey(operator record);
}