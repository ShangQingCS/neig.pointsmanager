package cn.sqhl.neig.pointsmanager.service;

import java.util.List;
import java.util.Map;

import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Advertise;
import cn.sqhl.neig.pointsmanager.vo.Eventsinfo;

public interface AdEventService extends BaseService{
	public Eventsinfo queryEvent(Map<String, Object> map);

	public List<Advertise> queryAD(PageCond page,Object obj);
}
