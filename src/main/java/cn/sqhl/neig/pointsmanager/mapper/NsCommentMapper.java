package cn.sqhl.neig.pointsmanager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.sqhl.neig.pointsmanager.core.PaginationInterceptor;
import cn.sqhl.neig.pointsmanager.po.NsComment;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Comment;

public interface NsCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsComment record);

    int insertSelective(NsComment record);

    NsComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsComment record);

    int updateByPrimaryKey(NsComment record);
    
    List<Comment> selectComment(@Param(PaginationInterceptor.PAGE_PARAM_KEY) PageCond page,@Param("map") Map<String, Object> map);
}