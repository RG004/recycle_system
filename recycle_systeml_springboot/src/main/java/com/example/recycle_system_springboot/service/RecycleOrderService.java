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


    PageInfo<RecycleOrdersVo> userfindOrdersByCollectorname(int id,String collectorname,String datebyday,String datebymonth,String datepick,int start,int limit);

    PageInfo<DoingOrdersVo> userfindDoingOrdersByCollectorname(int id,String collectorname,int start,int limit);

    PageInfo<CollectorOrdersVo> collectorfindOrdersByUsername(int id,String username,String datebyday,String datebymonth,String datepick,int start,int limit);

    PageInfo<CollectorDoingOrdersVo> collectorfindDoingOrdersByUsername(int id,String username,int start,int limit);

    List<EchartsTimeVo> userselectCountbytime(int id);
    List<EchartsTimeVo> userselectCountbydate(int id);
    List<EchartsTimeVo> adminselectCountbydate();
    List<EchartsTimeVo> adminselectCountbytime();
    List<EchartsTimeVo> adminselectAmountbydate();
    List<EchartsTimeVo> collectorselectCountbydate(int collectorid);
    List<EchartsTimeVo> collectorselectCountbytime(int collectorid);
    List<EchartsTimeVo> collectorselectAmountbydate(int collectorid);
    PageInfo<AllOrdersVo> adminfindAllOrders(String username,String collectorname,String datebyday,String datebymonth,String datepick,int start,int limit);

    PageInfo<AllDoingOrdersVo> adminfindAllDoingOrders(String username,String collectorname,String datebyday,String datebymonth,String datepick,int start,int limit);

    //得到所有的items
    List<ItemVo> getAllItems();
    //下单
    Boolean placeAnOrder(OrderDto orderVo);

    Boolean updateAnOrder(OrderDto orderVo);

    RecycleOrders findAnOrder(int id);

    Boolean placecollector(int recycleorderId,String collectorname);

    Boolean confirmOrder(OrderDto orderVo);

}
