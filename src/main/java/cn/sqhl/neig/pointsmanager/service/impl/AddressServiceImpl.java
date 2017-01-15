package cn.sqhl.neig.pointsmanager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sqhl.neig.pointsmanager.mapper.NsAddressMapper;
import cn.sqhl.neig.pointsmanager.po.NsAddress;
import cn.sqhl.neig.pointsmanager.service.AddressService;
import cn.sqhl.neig.pointsmanager.vo.Address;

@Service("addressService")
public class AddressServiceImpl implements AddressService{

	@Autowired
	private NsAddressMapper nsAddressMapper;
	
	public List<Address> queryObj(Map<String, Object> map) {
		
		List<Address> adds= nsAddressMapper.selectAddress(map);
		
		return adds;
	}

	public int addObj(NsAddress address) throws Exception{
		Map map=new HashMap();
		map.put("userid", address.getUerid());
		List<NsAddress> adds= nsAddressMapper.selectNsAddress(map);
		int i=0;
		int k=0;
		if(adds!=null && adds.size()>0){
			if(address.getIsuse()==0){
				for(NsAddress ads:adds){
					if(ads.getUerid()==0){
						i=i+1;
						ads.setIsuse(1);
						k=nsAddressMapper.updateByPrimaryKeySelective(ads);
					}
				}
			}
		}else{
			address.setIsuse(0);
		}
		if(i>0 && k==0){
			throw new RuntimeException("tansaction 异常 数据回滚");
		}
		k=nsAddressMapper.insert(address);
		if(k==0){
			throw new RuntimeException("tansaction 异常 数据回滚");
		}
		return k;
	}

	public int removeObj(NsAddress address) {
		return nsAddressMapper.deleteByPrimaryKey(address.getId());
	}

	@Transactional
	public int updateObj(NsAddress address) throws Exception{
		int i=0;
		i=nsAddressMapper.updateByPrimaryKeySelective(address);
		if(i>0){
			i=nsAddressMapper.updateIsUseExcept(address);
			if(i<=0){
				throw new RuntimeException("tansaction 异常 数据回滚");
			}
		}else{
			throw new RuntimeException("tansaction 异常 数据回滚");
		}
		return i;
	}

	public NsAddress queryByPrimaryKey(Long id) {
		return  nsAddressMapper.selectByPrimaryKey(id);
	}

}
