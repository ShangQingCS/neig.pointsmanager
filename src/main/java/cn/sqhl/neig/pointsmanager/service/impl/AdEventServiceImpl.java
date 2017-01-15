package cn.sqhl.neig.pointsmanager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sqhl.neig.pointsmanager.mapper.NsAdvertiseMapper;
import cn.sqhl.neig.pointsmanager.mapper.NsDictionariesMapper;
import cn.sqhl.neig.pointsmanager.mapper.NsEventsinfoMapper;
import cn.sqhl.neig.pointsmanager.service.AdEventService;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Advertise;
import cn.sqhl.neig.pointsmanager.vo.Dictionaries;
import cn.sqhl.neig.pointsmanager.vo.EventGoods;
import cn.sqhl.neig.pointsmanager.vo.Eventsinfo;

@Service("adEventService")
public class AdEventServiceImpl implements AdEventService{

	@Autowired
	private NsAdvertiseMapper nsAdvertiseMapper;
	
	@Autowired
	private NsEventsinfoMapper nsEventsinfoMapper;
	
	@Autowired
	private NsDictionariesMapper nsDictionariesMapper; 
	
	 
	public Eventsinfo queryEvent(Map<String, Object> map) {
		return nsEventsinfoMapper.selectById(Long.parseLong(map.get("eventsid")+""));
	}
	
	 
	public List<EventGoods> queryGoodsList(PageCond page,Object id) {
		return nsEventsinfoMapper.queryList(page,Long.parseLong(id+""));
	}

	 
	public List<Advertise> queryAD(PageCond page, Object obj) {
		return nsAdvertiseMapper.queryByMap(page, (Map<String, Object>)obj);
	}

	 
	public List<Object> queryObj(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public int addObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	 
	public int removeObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	 
	public int updateObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	 
	public List<Dictionaries> queryDictionary(Map map) {
		return nsDictionariesMapper.selectDictionaries(map);
	}

}
