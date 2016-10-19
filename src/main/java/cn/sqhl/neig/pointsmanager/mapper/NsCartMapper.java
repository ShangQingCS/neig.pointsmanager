package cn.sqhl.neig.pointsmanager.mapper;

import cn.sqhl.neig.pointsmanager.po.NsCart;

public interface NsCartMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsCart record);

    int insertSelective(NsCart record);

    NsCart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsCart record);

    int updateByPrimaryKey(NsCart record);
}