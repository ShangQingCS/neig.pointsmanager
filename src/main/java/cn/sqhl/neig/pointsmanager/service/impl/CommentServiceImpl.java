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
	
	@Override
	public List<Comment> queryObj(PageCond page, Map<String, Object> map) {
		return nsCommentMapper.queryComment(page,map);
	}

	@Override
	public List<Object> queryObj(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addObj(Object obj) {
		return nsCommentMapper.insertSelective((NsComment)obj);
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
