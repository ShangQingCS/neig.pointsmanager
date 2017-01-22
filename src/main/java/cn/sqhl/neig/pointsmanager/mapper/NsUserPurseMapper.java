package cn.sqhl.neig.pointsmanager.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.sqhl.neig.pointsmanager.po.NsUserPurse;

public interface NsUserPurseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Object record);

    int insertSelective(NsUserPurse record);

    NsUserPurse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsUserPurse record);

    int updateByPrimaryKey(NsUserPurse record);
    
    List<NsUserPurse> selectByUserId(@Param(value="userId")Long UserId,@Param(value="createTime")Date createTime,@Param(value="beforeTime")Date beforeTime);

	List<NsUserPurse> queryByUserId(Long id, String pursetype, Date date);

	List<Object> queryObj(Map<String, Object> map);
}