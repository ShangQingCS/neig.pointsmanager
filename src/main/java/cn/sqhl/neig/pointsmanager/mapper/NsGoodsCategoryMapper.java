package cn.sqhl.neig.pointsmanager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.sqhl.neig.pointsmanager.core.PaginationInterceptor;
import cn.sqhl.neig.pointsmanager.po.NsGoodsCategory;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.GoodsCategory;

public interface NsGoodsCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NsGoodsCategory record);

    int insertSelective(NsGoodsCategory record);

    NsGoodsCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NsGoodsCategory record);

    int updateByPrimaryKey(NsGoodsCategory record);
    
    List<GoodsCategory> queryByParentId(@Param(PaginationInterceptor.PAGE_PARAM_KEY) PageCond page,@Param("map") Map<String, Object> map);
}