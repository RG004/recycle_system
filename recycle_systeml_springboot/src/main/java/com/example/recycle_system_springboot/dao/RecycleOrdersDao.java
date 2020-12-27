package com.example.recycle_system_springboot.dao;

import com.example.recycle_system_springboot.pojo.entity.RecycleOrders;
import com.example.recycle_system_springboot.pojo.vo.CollectorDoingOrdersVo;
import com.example.recycle_system_springboot.pojo.vo.CollectorOrdersVo;
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

    Page<RecycleOrdersVo> selectOrderByUserId(Integer userid);//查找用户所有的订单

    Page<DoingOrdersVo> selectDoingOrderByUserId(Integer userid);//查找用户正在进行中的订单

    Page<CollectorOrdersVo> selectOrderByCollectorId(Integer collectorid);//查找派送员所有的订单

    Page<CollectorDoingOrdersVo> selectDoingOrderByCollectorId(Integer collectorid);//查找派送员正在进行中的订单

    Page<RecycleOrdersVo> selectOrderByCollectorname(Integer userid,String collectorname,String date);//用户根据日期和派送员姓名查找

    Page<RecycleOrdersVo> selectOrderByCollectornameBymonth(Integer userid,String collectorname,String date);//用户根据日期和派送员姓名查找

    Page<DoingOrdersVo> selectDoingOrderByCollectorname(Integer userid,String collectorname);
}
