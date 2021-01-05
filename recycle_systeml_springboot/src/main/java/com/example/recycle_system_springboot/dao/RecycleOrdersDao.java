package com.example.recycle_system_springboot.dao;

import com.example.recycle_system_springboot.pojo.entity.RecycleOrders;
import com.example.recycle_system_springboot.pojo.vo.*;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

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

    Page<RecycleOrdersVo> selectOrderByCollectorname(Integer userid,String collectorname,String date);//用户根据日期(查询某一天)和派送员姓名查找

    Page<RecycleOrdersVo> selectOrderByCollectornameBymonth(Integer userid,String collectorname,String date);//用户根据日期(查询某一月)和派送员姓名查找

    Page<DoingOrdersVo> selectDoingOrderByCollectorname(Integer userid,String collectorname);

    Page<CollectorOrdersVo> selectOrderByCollectorId(Integer collectorid);//查找派送员所有的订单

    Page<CollectorDoingOrdersVo> selectDoingOrderByCollectorId(Integer collectorid);//查找派送员正在进行中的订单

    Page<CollectorOrdersVo> CollectorselctOrderByUsername(Integer collectorid,String username,String date);

    Page<CollectorOrdersVo> CollectorselctOrderByUsernameBymonth(Integer collectorid,String username,String date);

    Page<CollectorDoingOrdersVo> CollectorselectDoingOrderByUsername(Integer collectorid,String username);

    List<EchartsTimeVo> userselectCountbytime(Integer id);
    List<EchartsTimeVo> userselectCountbydate(Integer id);
    List<EchartsTimeVo> adminselectCountbytime();
    List<EchartsTimeVo> adminselectCountbydate();

    Page<AllOrdersVo> selectAllByadmin(String username,String collectorname,String date,String datepick);

    Page<AllDoingOrdersVo> selectDoingByadmin(String username,String collectorname,String date,String datepick);

    int updateCollector(@Param("recycleOrderId") int recycleOrderId, @Param("collectorId") int collectorId);

}
