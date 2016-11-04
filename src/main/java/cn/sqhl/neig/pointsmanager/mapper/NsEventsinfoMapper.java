package cn.sqhl.neig.pointsmanager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sqhl.neig.pointsmanager.core.PaginationInterceptor;
import cn.sqhl.neig.pointsmanager.po.NsEventsinfo;
import cn.sqhl.neig.pointsmanager.po.NsEventsinfoWithBLOBs;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Eventsinfo;

public interface NsEventsinfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsEventsinfoWithBLOBs record);

    int insertSelective(NsEventsinfoWithBLOBs record);

    NsEventsinfoWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsEventsinfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(NsEventsinfoWithBLOBs record);

    int updateByPrimaryKey(NsEventsinfo record);
    
    Eventsinfo queryListPageById(@Param(PaginationInterceptor.PAGE_PARAM_KEY) PageCond page,@Param("id") Long id);
}