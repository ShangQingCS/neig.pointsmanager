package cn.sqhl.neig.pointsmanager.mapper;

import java.util.List;

import cn.sqhl.neig.pointsmanager.po.NsUserGrade;

public interface NsUserGradeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsUserGrade record);

    int insertSelective(NsUserGrade record);

    NsUserGrade selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsUserGrade record);

    int updateByPrimaryKey(NsUserGrade record);
    
    List<NsUserGrade> selectUserGrade();
}