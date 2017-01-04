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
	
	@Override
	public List<Object> queryObj(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addObj(Object obj) {
		return nsUserMapper.insertSelective((NsUser)obj);
	}

	@Override
	public int removeObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateObj(Object obj) {
		// TODO Auto-generated method stub
		return nsUserMapper.updateByPrimaryKeySelective((NsUser)obj);
	}

	@Override
	public NsUser queryByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return nsUserMapper.selectByPrimaryKey(new Long(id));
	}

	@Override
	public List<NsUser> selectUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	


	@Override
	public NsUser queryByUserName(String userName,String loginPwd) {
		// TODO Auto-generated method stub
		return nsUserMapper.selectByUserName(userName,loginPwd);
	}

	@Override
	public NsUser queryByUserPhone(String userPhone,String loginPwd) {
		// TODO Auto-generated method stub
		return nsUserMapper.selectByUserPhone(userPhone,loginPwd);
	}
	
	
	
}
