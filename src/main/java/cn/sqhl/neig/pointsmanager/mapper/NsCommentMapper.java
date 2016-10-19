package cn.sqhl.neig.pointsmanager.mapper;

import cn.sqhl.neig.pointsmanager.po.NsComment;

public interface NsCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsComment record);

    int insertSelective(NsComment record);

    NsComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsComment record);

    int updateByPrimaryKey(NsComment record);
}