package cn.sqhl.neig.pointsmanager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.sqhl.neig.pointsmanager.po.NsUser;

public interface NsUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NsUser record);

    int insertSelective(NsUser record);

    NsUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NsUser record);

    int updateByPrimaryKey(NsUser record);
    
    List<NsUser> selectUser(@Param("map") Map<String, Object> map);
}