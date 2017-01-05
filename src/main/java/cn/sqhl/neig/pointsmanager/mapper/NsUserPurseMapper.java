package cn.sqhl.neig.pointsmanager.mapper;

import java.util.Date;
import java.util.List;

import cn.sqhl.neig.pointsmanager.po.NsUserPurse;

public interface NsUserPurseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsUserPurse record);

    int insertSelective(NsUserPurse record);

    NsUserPurse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsUserPurse record);

    int updateByPrimaryKey(NsUserPurse record);
    
    List<NsUserPurse> selectByUserId(Long UserId,Date createTime);
}