package cn.sqhl.neig.pointsmanager.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sqhl.neig.pointsmanager.mapper.NsUserPurseMapper;
import cn.sqhl.neig.pointsmanager.po.NsUserPurse;
import cn.sqhl.neig.pointsmanager.service.UserPurseService;
import cn.sqhl.neig.pointsmanager.utils.DateHelper;
@Service("userPurseService")
public class UserPurseServiceImpl implements UserPurseService{
	@Autowired
	private NsUserPurseMapper nsUserPurseMapper;
	
	@Override
	public List<Object> queryObj(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<NsUserPurse> selectByUserId(Long UserId, 
			Date beforeTime) {
		
		return nsUserPurseMapper.selectByUserId(UserId,new Date() , beforeTime);
	}

	

}
