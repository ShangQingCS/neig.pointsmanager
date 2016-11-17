package cn.sqhl.neig.pointsmanager.mapper;

import cn.sqhl.neig.pointsmanager.po.NsEventsinfo;
import cn.sqhl.neig.pointsmanager.po.NsEventsinfoWithBLOBs;
import cn.sqhl.neig.pointsmanager.vo.Eventsinfo;

public interface NsEventsinfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsEventsinfoWithBLOBs record);

    int insertSelective(NsEventsinfoWithBLOBs record);

    NsEventsinfoWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsEventsinfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(NsEventsinfoWithBLOBs record);

    int updateByPrimaryKey(NsEventsinfo record);
    
    Eventsinfo selectById(Long id);
}