package cn.sqhl.neig.pointsmanager.mapper;

import cn.sqhl.neig.pointsmanager.po.NsAddress;

public interface NsAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsAddress record);

    int insertSelective(NsAddress record);

    NsAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsAddress record);

    int updateByPrimaryKey(NsAddress record);
}