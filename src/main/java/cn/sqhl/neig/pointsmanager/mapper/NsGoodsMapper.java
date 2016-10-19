package cn.sqhl.neig.pointsmanager.mapper;

import cn.sqhl.neig.pointsmanager.po.NsGoods;

public interface NsGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsGoods record);

    int insertSelective(NsGoods record);

    NsGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsGoods record);

    int updateByPrimaryKeyWithBLOBs(NsGoods record);

    int updateByPrimaryKey(NsGoods record);
}