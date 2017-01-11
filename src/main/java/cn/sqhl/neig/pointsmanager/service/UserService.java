package cn.sqhl.neig.pointsmanager.service;

import java.util.List;
import java.util.Map;

import cn.sqhl.neig.pointsmanager.po.NsUser;

public interface UserService extends BaseService{

	public NsUser queryByPrimaryKey(Integer id);
	
	public List<NsUser> selectUser(Map<String, Object> map);
	
	public NsUser queryByUserName(String userName,String loginPwd);
	
	public NsUser queryByUserPhone(String userPhone,String loginPwd);
	
	public int updateByNickName(NsUser user);
	
	public int updateByIDcard(NsUser user);
	
	public int updateByLoginPwd(NsUser user);
    
	public int  updateByPayPwd(NsUser user);
	
	public List<NsUser> queryByUserPid(Long id);
	
}
