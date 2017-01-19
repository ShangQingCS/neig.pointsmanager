package cn.sqhl.neig.pointsmanager.service;

import java.util.Date;
import java.util.List;

import cn.sqhl.neig.pointsmanager.po.NsUserPurse;

public interface UserPurseService extends BaseService{

	public List<NsUserPurse> selectByUserId(Long userId,Date beforeTime);
	
	public List<NsUserPurse> queryByUserId(Long id, String pursetype, Date date);
}
