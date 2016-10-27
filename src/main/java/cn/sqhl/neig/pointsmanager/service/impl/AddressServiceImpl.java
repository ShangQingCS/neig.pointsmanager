package cn.sqhl.neig.pointsmanager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sqhl.neig.pointsmanager.mapper.NsAddressMapper;
import cn.sqhl.neig.pointsmanager.po.NsAddress;
import cn.sqhl.neig.pointsmanager.service.AddressService;
import cn.sqhl.neig.pointsmanager.vo.Address;

@Service("addressService")
public class AddressServiceImpl implements AddressService{

	@Autowired
	private NsAddressMapper nsAddressMapper;
	
	@Override
	public List<Address> queryObj(Map<String, Object> map) {
		
		List<Address> adds= nsAddressMapper.selectAddress(map);
		
		return adds;
	}

	@Override
	public int addObj(NsAddress address) {
		
		return nsAddressMapper.insert(address);
	}

	@Override
	public int removeObj(NsAddress address) {
		return nsAddressMapper.deleteByPrimaryKey(address.getId());
	}

	@Override
	public int updateObj(NsAddress address) {
		return nsAddressMapper.updateByPrimaryKey(address);
	}

}
