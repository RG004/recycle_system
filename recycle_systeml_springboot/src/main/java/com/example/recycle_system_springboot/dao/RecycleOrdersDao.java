package com.example.recycle_system_springboot.dao;

import com.example.recycle_system_springboot.pojo.entity.RecycleOrders;
import com.example.recycle_system_springboot.pojo.vo.DoingOrdersVo;
import com.example.recycle_system_springboot.pojo.vo.RecycleOrdersVo;
import com.github.pagehelper.Page;

import java.util.List;

public interface RecycleOrdersDao {
    int deleteByPrimaryKey(Integer recycleOrderId);

    int insert(RecycleOrders record);

    int insertSelective(RecycleOrders record);

    RecycleOrders selectByPrimaryKey(Integer recycleOrderId);

    int updateByPrimaryKeySelective(RecycleOrders record);

    int updateByPrimaryKey(RecycleOrders record);

    Page<RecycleOrdersVo> selectOrderByUserId(Integer userid);

    Page<DoingOrdersVo> selectDoingOrderByUserId(Integer userid);
}
