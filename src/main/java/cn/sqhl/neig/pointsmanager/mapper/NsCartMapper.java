package cn.sqhl.neig.pointsmanager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.sqhl.neig.pointsmanager.core.PaginationInterceptor;
import cn.sqhl.neig.pointsmanager.po.NsCart;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Cart;

public interface NsCartMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByUserId(@Param("id")Long id,@Param("userid")Long userid);

    int insert(NsCart record);

    int insertSelective(NsCart record);

    NsCart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsCart record);

    int updateByPrimaryKey(NsCart record);
    
    List<Cart> queryMapList(@Param(PaginationInterceptor.PAGE_PARAM_KEY) PageCond page,@Param("userid") Long userid);
    
    List<Object> selectMapList(@Param("map") Map<String, Object> map);
    
    List<Cart> selectList(@Param("map") Map<String, Object> map);
    
    
}