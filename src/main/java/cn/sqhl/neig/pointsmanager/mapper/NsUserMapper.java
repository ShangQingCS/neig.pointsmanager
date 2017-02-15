package cn.sqhl.neig.pointsmanager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.sqhl.neig.pointsmanager.core.PaginationInterceptor;
import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Order;

public interface NsUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsUser record);

    int insertSelective(NsUser record);

    NsUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsUser record);

    int updateByPrimaryKey(NsUser record);
    
    int updateByNickName(NsUser user);
    
    int updateByIDcard(NsUser user);
   
    int updateByLoginPwd(NsUser user);
    
    int  updateByPayPwd(NsUser user);
    
    NsUser selectByUserName(@Param(value="userName")String userName,@Param(value="loginPwd")String loginPwd);
    
    NsUser selectByUserPhone(@Param(value="userPhone")String userPhone,@Param(value="loginPwd")String loginPwd);
    
    List<NsUser> queryByUserPid(@Param(PaginationInterceptor.PAGE_PARAM_KEY) PageCond page,@Param("id")Long id);
}