package cn.sqhl.neig.pointsmanager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sqhl.neig.pointsmanager.mapper.NsUserMapper;
import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private NsUserMapper nsUserMapper;
	
	
	public List<Object> queryObj(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int addObj(Object obj) {
		return nsUserMapper.insertSelective((NsUser)obj);
	}

	
	public int removeObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateObj(Object obj) {
		// TODO Auto-generated method stub
		return nsUserMapper.updateByPrimaryKeySelective((NsUser)obj);
	}

	
	public NsUser queryByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return nsUserMapper.selectByPrimaryKey(new Long(id));
	}

	
	public List<NsUser> selectUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	


	
	public NsUser queryByUserName(String userName,String loginPwd) {
		// TODO Auto-generated method stub
		return nsUserMapper.selectByUserName(userName,loginPwd);
	}

	
	public NsUser queryByUserPhone(String userPhone,String loginPwd) {
		// TODO Auto-generated method stub
		return nsUserMapper.selectByUserPhone(userPhone,loginPwd);
	}

	
	public int updateByNickName(NsUser user) {
		// TODO Auto-generated method stub
		return nsUserMapper.updateByNickName(user);
	}

	
	public int updateByIDcard(NsUser user) {
		// TODO Auto-generated method stub
		return nsUserMapper.updateByIDcard(user);
	}

	
	public int updateByLoginPwd(NsUser user) {
		// TODO Auto-generated method stub
		return nsUserMapper.updateByLoginPwd(user);
	}

	
	public int updateByPayPwd(NsUser user) {
		// TODO Auto-generated method stub
		return nsUserMapper.updateByPayPwd(user);
	}

	
	public List<NsUser> queryByUserPid(Long id) {
		// TODO Auto-generated method stub
		return nsUserMapper.selectByUserPid(id);
	}
	
	
	
}
