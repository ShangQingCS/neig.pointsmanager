package cn.sqhl.neig.pointsmanager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.sqhl.neig.pointsmanager.core.PaginationInterceptor;
import cn.sqhl.neig.pointsmanager.po.NsOrder;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Order;

public interface NsOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsOrder record);

    int insertSelective(NsOrder record);

    NsOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsOrder record);

    int updateByPrimaryKey(NsOrder record);
    
    List<Order> queryOrder(@Param(PaginationInterceptor.PAGE_PARAM_KEY) PageCond page,@Param("map") Map<String, Object> map);
    
    int insertOrder(NsOrder record);
}