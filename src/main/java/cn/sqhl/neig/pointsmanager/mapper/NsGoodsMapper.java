package cn.sqhl.neig.pointsmanager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sqhl.neig.pointsmanager.po.NsGoods;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Goods;
import cn.sqhl.neig.pointsmanager.core.PaginationInterceptor;

public interface NsGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsGoods record);

    int insertSelective(NsGoods record);

    NsGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsGoods record);

    int updateByPrimaryKeyWithBLOBs(NsGoods record);

    int updateByPrimaryKey(NsGoods record);
    
    List<Goods> selectListPageByLike(@Param(PaginationInterceptor.PAGE_PARAM_KEY) PageCond page,@Param("searchcode") String searchcode);
    
    List<Goods> selectListPageByParentID(@Param(PaginationInterceptor.PAGE_PARAM_KEY) PageCond page,@Param("parentid") String parentid);
}