package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.vo.*;

import com.github.pagehelper.PageInfo;

import java.util.List;


public interface RecycleOrderService {
    PageInfo<RecycleOrdersVo> userfindAllOrders(int id,int start,int limit);

    PageInfo<DoingOrdersVo> userfindDoingOrders(int id,int start,int limit);

    PageInfo<CollectorOrdersVo> collectorfindAllOrders(int id,int start,int limit);

    PageInfo<CollectorDoingOrdersVo> collectorfindDoingOrders(int id, int start, int limit);

    PageInfo<RecycleOrdersVo> userfindOrdersByCollectorname(int id,String collectorname,String datebyday,String datebymonth,String datepick,int start,int limit);

    PageInfo<DoingOrdersVo> userfindDoingOrdersByCollectorname(int id,String collectorname,int start,int limit);

    PageInfo<CollectorOrdersVo> collectorfindOrdersByUsername(int id,String username,String datebyday,String datebymonth,String datepick,int start,int limit);

    PageInfo<CollectorDoingOrdersVo> collectorfindDoingOrdersByUsername(int id,String username,int start,int limit);

    List<EchartsTimeVo> echartsBytime(int id);
}
