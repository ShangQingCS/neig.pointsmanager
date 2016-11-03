package cn.sqhl.neig.pointsmanager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.sqhl.neig.pointsmanager.core.PaginationInterceptor;
import cn.sqhl.neig.pointsmanager.po.NsGoods;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Goods;

public interface NsGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsGoods record);

    int insertSelective(NsGoods record);

    NsGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsGoods record);

    int updateByPrimaryKeyWithBLOBs(NsGoods record);

    int updateByPrimaryKey(NsGoods record);
    
    List<Goods> selectListPageByParentID(@Param(PaginationInterceptor.PAGE_PARAM_KEY) PageCond page,@Param("map") Map<String, Object> map);
    
    List<Goods> selectListPageByLike(@Param(PaginationInterceptor.PAGE_PARAM_KEY) PageCond page,@Param("map") Map<String, Object> map);
}