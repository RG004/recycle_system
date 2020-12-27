package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.vo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface RecycleOrderService {
    PageInfo<RecycleOrdersVo> userfindAllOrders(int id,int start,int limit);

    PageInfo<DoingOrdersVo> userfindDoingOrders(int id,int start,int limit);

    PageInfo<CollectorOrdersVo> collectorfindAllOrders(int id,int start,int limit);

    PageInfo<CollectorDoingOrdersVo> collectorfindDoingOrders(int id, int start, int limit);

    //得到所有的items
    List<ItemVo> getAllItems();

    //下单
    Boolean placeAnOrder(OrderVo orderVo);
}
