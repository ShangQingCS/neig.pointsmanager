package cn.sqhl.neig.pointsmanager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sqhl.neig.pointsmanager.mapper.NsAdvertiseMapper;
import cn.sqhl.neig.pointsmanager.mapper.NsEventsinfoMapper;
import cn.sqhl.neig.pointsmanager.service.AdEventService;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Advertise;
import cn.sqhl.neig.pointsmanager.vo.Eventsinfo;

@Service("adEventService")
public class AdEventServiceImpl implements AdEventService{

	@Autowired
	private NsAdvertiseMapper nsAdvertiseMapper;
	
	@Autowired
	private NsEventsinfoMapper nsEventsinfoMapper;
	
	@Override
	public Eventsinfo queryEvent(Map<String, Object> map) {
		return nsEventsinfoMapper.selectById(Long.parseLong(map.get("eventsid")+""));
	}

	@Override
	public List<Advertise> queryAD(PageCond page, Object obj) {
		return nsAdvertiseMapper.queryByMap(page, (Map<String, Object>)obj);
	}

	@Override
	public List<Object> queryObj(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

}
