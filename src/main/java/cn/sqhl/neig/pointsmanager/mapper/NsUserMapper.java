package cn.sqhl.neig.pointsmanager.mapper;

import org.apache.ibatis.annotations.Param;

import cn.sqhl.neig.pointsmanager.po.NsUser;

public interface NsUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsUser record);

    int insertSelective(NsUser record);

    NsUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsUser record);

    int updateByPrimaryKey(NsUser record);
    
    NsUser selectByUserName(@Param(value="userName")String userName,@Param(value="loginPwd")String loginPwd);
    
    NsUser selectByUserPhone(@Param(value="userPhone")String userPhone,@Param(value="loginPwd")String loginPwd);
}