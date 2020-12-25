package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.vo.DoingOrdersVo;
import com.example.recycle_system_springboot.pojo.vo.RecycleOrdersVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;


public interface RecycleOrderService {
    PageInfo<RecycleOrdersVo> userfindAllOrders(int id,int start,int limit);

    PageInfo<DoingOrdersVo> userfindDoingOrders(int id,int start,int limit);

    PageInfo<RecycleOrdersVo> userfindOrdersBycellector(int id,String cellectorname,int start,int limit);

    PageInfo<RecycleOrdersVo> userfindDayOrders(int id, int start, int limit, String day);

    PageInfo<RecycleOrdersVo> userfindMonthOrders(int id, int start, int limit, String month);
}
