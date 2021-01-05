package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.dto.OrderDto;
import com.example.recycle_system_springboot.pojo.entity.RecycleOrders;
import com.example.recycle_system_springboot.pojo.vo.*;
import com.github.pagehelper.PageInfo;
import java.util.List;


public interface RecycleOrderService {

    PageInfo<OrdersVo> userfindAllOrders(int userid,String collectorname,String datebyday,String datebymonth,String datepick,int start,int limit);

    PageInfo<OrdersVo> userfindAllDoingOrders(int userid,String collectorname,String datebyday,String datebymonth,String datepick,int start,int limit);

    PageInfo<OrdersVo> collectorfindAllOrders(int collectorid,String collectorname,String datebyday,String datebymonth,String datepick,int start,int limit);

    PageInfo<OrdersVo> collectorfindAllDoingOrders(int collectorid,String collectorname,String datebyday,String datebymonth,String datepick,int start,int limit);

    PageInfo<OrdersVo> adminfindAllOrders(String username,String collectorname,String datebyday,String datebymonth,String datepick,int start,int limit);

    PageInfo<OrdersVo> adminfindAllDoingOrders(String username,String collectorname,String datebyday,String datebymonth,String datepick,int start,int limit);

    //得到所有的items
    List<ItemVo> getAllItems();
    //下单
    Boolean placeAnOrder(OrderDto orderVo);

    //确认订单
    Boolean confirmOrder(OrderDto orderVo);

    Boolean updateAnOrder(OrderDto orderVo);

    RecycleOrders findAnOrder(int id);

    Boolean placecollector(int recycleorderId,String collectorname);

    Boolean confirmOrder(OrderDto orderVo);
}
