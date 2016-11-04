package cn.sqhl.neig.pointsmanager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sqhl.neig.pointsmanager.core.PaginationInterceptor;
import cn.sqhl.neig.pointsmanager.po.NsAdvertise;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Advertise;

public interface NsAdvertiseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsAdvertise record);

    int insertSelective(NsAdvertise record);

    NsAdvertise selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsAdvertise record);

    int updateByPrimaryKeyWithBLOBs(NsAdvertise record);

    int updateByPrimaryKey(NsAdvertise record);
    
    List<Advertise> queryListPageByType(@Param(PaginationInterceptor.PAGE_PARAM_KEY) PageCond page,@Param("type") Integer id);
}