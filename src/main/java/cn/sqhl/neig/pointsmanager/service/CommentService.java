package cn.sqhl.neig.pointsmanager.service;

import java.util.List;
import java.util.Map;

import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Comment;

public interface CommentService extends BaseService{
	
	public List<Comment> queryComment(PageCond page,Map<String, Object> map);

}
