package cn.sqhl.neig.pointsmanager.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sqhl.neig.pointsmanager.po.NsOrderDetail;
import cn.sqhl.neig.pointsmanager.vo.OrderDetail;

public interface NsOrderDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsOrderDetail record);

    int insertSelective(NsOrderDetail record);

    NsOrderDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsOrderDetail record);

    int updateByPrimaryKey(NsOrderDetail record);
    
//    List<OrderDetail> selectOrderDetail(Integer id);
}