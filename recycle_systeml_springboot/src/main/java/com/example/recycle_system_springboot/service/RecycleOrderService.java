package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.vo.CollectorDoingOrdersVo;
import com.example.recycle_system_springboot.pojo.vo.CollectorOrdersVo;
import com.example.recycle_system_springboot.pojo.vo.DoingOrdersVo;
import com.example.recycle_system_springboot.pojo.vo.RecycleOrdersVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;


public interface RecycleOrderService {
    PageInfo<RecycleOrdersVo> userfindAllOrders(int id,int start,int limit);

    PageInfo<DoingOrdersVo> userfindDoingOrders(int id,int start,int limit);

    PageInfo<CollectorOrdersVo> collectorfindAllOrders(int id,int start,int limit);

    PageInfo<CollectorDoingOrdersVo> collectorfindDoingOrders(int id, int start, int limit);

    PageInfo<RecycleOrdersVo> userfindOrdersByCollectorname(int id,String collectorname,String datebyday,String datebymonth,String datepick,int start,int limit);

    PageInfo<DoingOrdersVo> userfindDoingOrdersByCollectorname(int id,String collectorname,int start,int limit);
}
