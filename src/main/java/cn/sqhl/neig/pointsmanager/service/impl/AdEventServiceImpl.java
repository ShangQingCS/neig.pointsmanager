package cn.sqhl.neig.pointsmanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sqhl.neig.pointsmanager.mapper.NsAdvertiseMapper;
import cn.sqhl.neig.pointsmanager.mapper.NsEventsinfoMapper;
import cn.sqhl.neig.pointsmanager.service.AdEventService;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Advertise;

@Service("adEventService")
public class AdEventServiceImpl implements AdEventService{

	@Autowired
	private NsAdvertiseMapper nsAdvertiseMapper;
	
	@Autowired
	private NsEventsinfoMapper nsEventsinfoMapper;
	
	@Override
	public Object queryEvent(PageCond page, Object obj) {
		return nsEventsinfoMapper.queryListPageById(page, Long.parseLong(obj.toString()));
	}

	@Override
	public List<Advertise> queryAD(PageCond page, Object obj) {
		return nsAdvertiseMapper.queryListPageByType(page,Integer.parseInt(obj.toString()));
	}

}
