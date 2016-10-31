package cn.sqhl.neig.pointsmanager.service;

import java.util.List;

import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Advertise;

public interface AdEventService {
	public Object queryEvent(PageCond page,Object obj);
	
	public List<Advertise> queryAD(PageCond page,Object obj);
}
