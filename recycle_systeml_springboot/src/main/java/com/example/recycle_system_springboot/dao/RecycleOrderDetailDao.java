package com.example.recycle_system_springboot.dao;

import com.example.recycle_system_springboot.pojo.entity.RecycleOrderDetail;
import com.example.recycle_system_springboot.pojo.vo.DoingOrdersVo;
import com.example.recycle_system_springboot.pojo.vo.RecycleOrdersDetailVo;

import java.util.List;

public interface RecycleOrderDetailDao {
    int deleteByPrimaryKey(Integer recycleOrderDetailId);

    int insert(RecycleOrderDetail record);

    int insertSelective(RecycleOrderDetail record);

    RecycleOrderDetail selectByPrimaryKey(Integer recycleOrderDetailId);

    int updateByPrimaryKeySelective(RecycleOrderDetail record);

    int updateByPrimaryKey(RecycleOrderDetail record);

    List<RecycleOrdersDetailVo> selectDetailByRecycleOrderId(Integer recycleOrderId);
}
