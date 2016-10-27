package cn.sqhl.neig.pointsmanager.service;

import java.util.List;
import java.util.Map;


public interface BaseService {
	public List<Object> queryObj(Map<String, Object> map);
	
	public int addObj(Object obj);
	
	public int removeObj(Object obj);
	
	public int updateObj(Object obj);
}
