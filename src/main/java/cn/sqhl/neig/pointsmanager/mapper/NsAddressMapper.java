package cn.sqhl.neig.pointsmanager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.sqhl.neig.pointsmanager.po.NsAddress;
import cn.sqhl.neig.pointsmanager.vo.Address;

public interface NsAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsAddress record);

    int insertSelective(NsAddress record);

    NsAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsAddress record);

    int updateByPrimaryKey(NsAddress record);
    
    int updateIsUseExcept(NsAddress record);
    
    public List<Address> selectAddress(@Param("map") Map<String, Object> map);
    
    public List<NsAddress> selectNsAddress(@Param("map") Map<String, Object> map);
    
}