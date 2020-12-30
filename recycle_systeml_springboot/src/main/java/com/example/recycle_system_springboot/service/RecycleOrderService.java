package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.dto.OrderDto;
import com.example.recycle_system_springboot.pojo.entity.RecycleOrders;
import com.example.recycle_system_springboot.pojo.vo.*;
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
    Boolean placeAnOrder(OrderDto orderVo);
    //修改订单
    Boolean updateAnOrder(OrderDto orderVo);

    RecycleOrders findAnOrder(int id);
}
