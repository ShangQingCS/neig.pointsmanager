package cn.sqhl.neig.pointsmanager.mapper;

import cn.sqhl.neig.pointsmanager.po.NsGoodsCategory;

public interface NsGoodsCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NsGoodsCategory record);

    int insertSelective(NsGoodsCategory record);

    NsGoodsCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NsGoodsCategory record);

    int updateByPrimaryKey(NsGoodsCategory record);
}