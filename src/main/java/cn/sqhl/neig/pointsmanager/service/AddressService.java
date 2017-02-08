package cn.sqhl.neig.pointsmanager.service;

import java.util.List;
import java.util.Map;

import cn.sqhl.neig.pointsmanager.po.NsAddress;
import cn.sqhl.neig.pointsmanager.vo.Address;

public interface AddressService {

	public List<Address> queryObj(Map<String, Object> map);
	
	public int addObj(NsAddress address) throws Exception;
	
	public int removeObj(NsAddress address);
	
	public int updateObj(NsAddress address) throws Exception;
	
	public NsAddress queryByPrimaryKey(Long id);
	
	public NsAddress selectByUserId(Long userid,Integer isuse);
}
