package cn.sqhl.neig.pointsmanager.service;

import java.util.List;
import java.util.Map;

import cn.sqhl.neig.pointsmanager.po.NsAddress;
import cn.sqhl.neig.pointsmanager.vo.Address;

public interface AddressService {

	public List<Address> getaddress(Map<String, Object> map);
	
	public int addObj(NsAddress address);
	
	public int removeObj(NsAddress address);
	
	public int updateObj(NsAddress address);
}
