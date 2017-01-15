package cn.sqhl.neig.pointsmanager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sqhl.neig.pointsmanager.mapper.NsCommentMapper;
import cn.sqhl.neig.pointsmanager.po.NsComment;
import cn.sqhl.neig.pointsmanager.service.CommentService;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Comment;

@Service("commentService")
public class CommentServiceImpl implements CommentService{

	@Autowired
	NsCommentMapper nsCommentMapper;
	
	public List<Comment> queryComment(PageCond page,Map<String, Object> map){
		return nsCommentMapper.queryCommentList(page, map);
	}
	

	
	public List<Object> queryObj(Map<String, Object> map) {
		return  nsCommentMapper.selectCommentList(map);
	}

	
	public int addObj(Object obj) {
		return nsCommentMapper.insertSelective((NsComment)obj);
	}

	
	public int removeObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

}
