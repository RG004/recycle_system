package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.dto.OrderDto;
import com.example.recycle_system_springboot.pojo.entity.RecycleOrders;

import com.example.recycle_system_springboot.pojo.query.AdminReqirueQuery;
import com.example.recycle_system_springboot.pojo.query.CollectorRequireQuery;
import com.example.recycle_system_springboot.pojo.query.UserRequireQuery;

import com.example.recycle_system_springboot.pojo.vo.*;
import com.example.recycle_system_springboot.service.RecycleOrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RecycleOrderController {
    //用于查询所有订单、正在进行中的订单，其中用到了分页的技术
    @Resource
    RecycleOrderService recycleOrderService;




    @GetMapping("/userAllorders/{id}/{start}/{limit}")
    @ResponseBody
    public PageInfo<RecycleOrdersVo> userfindAllOrders(@PathVariable("id") int id,@PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<RecycleOrdersVo> result =recycleOrderService.userfindAllOrders(id,start,limit);
        return result;
    }

    @GetMapping("/userDoingorders/{id}/{start}/{limit}")
    @ResponseBody
    public PageInfo<DoingOrdersVo> userfindDoingOrders(@PathVariable("id") int id,@PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<DoingOrdersVo> result = recycleOrderService.userfindDoingOrders(id,start,limit);
        return result;
    }

    @GetMapping("/collectorAllorders/{id}/{start}/{limit}")
    @ResponseBody
    public PageInfo<CollectorOrdersVo> collectorfindAllOrders(@PathVariable("id") int id, @PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<CollectorOrdersVo> result = recycleOrderService.collectorfindAllOrders(id,start,limit);
        return result;
    }

    @GetMapping("/collectorDoingorders/{id}/{start}/{limit}")
    @ResponseBody
    public PageInfo<CollectorDoingOrdersVo> collectorfindDoingOrders(@PathVariable("id") int id, @PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<CollectorDoingOrdersVo> result = recycleOrderService.collectorfindDoingOrders(id,start,limit);
        return result;
    }



    @PostMapping("/userFindordersByrequire/{start}/{limit}")
    @ResponseBody
    public PageInfo<RecycleOrdersVo> selectOrderByCollectorname(@RequestBody UserRequireQuery u,@PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<RecycleOrdersVo> result = recycleOrderService.userfindOrdersByCollectorname(u.getId(),u.getCollectorname(),u.getDatebyday(),u.getDatebymonth(),u.getDatepick(),start,limit);
        return result;
    }

    @GetMapping("/userFinddoingordersBycollectorname/{id}/{collectorname}/{start}/{limit}")
    @ResponseBody
    public PageInfo<DoingOrdersVo> selectDoingOrderByCollectorname(@PathVariable("id") int id,@PathVariable("collectorname") String collectorname,@PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<DoingOrdersVo> result = recycleOrderService.userfindDoingOrdersByCollectorname(id,collectorname,start,limit);
        return result;
    }
    @PostMapping("/collectorFindordersByrequire/{start}/{limit}")
    @ResponseBody
    public PageInfo<CollectorOrdersVo> selectOrderByUsername(@RequestBody CollectorRequireQuery c, @PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<CollectorOrdersVo> result = recycleOrderService.collectorfindOrdersByUsername(c.getId(),c.getUsername(),c.getDatebyday(),c.getDatebymonth(),c.getDatepick(),start,limit);
        return result;
    }

    @GetMapping("/collectorDoingordersByusername/{id}/{username}/{start}/{limit}")
    @ResponseBody
    public PageInfo<CollectorDoingOrdersVo> collectorfindDoingOrdersByusername(@PathVariable("id") int id,  @PathVariable("username") String username,@PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<CollectorDoingOrdersVo> result = recycleOrderService.collectorfindDoingOrdersByUsername(id,username,start,limit);
        return result;
    }

    //Echarts根据预约时间统计订单
    @GetMapping("/collectorselectCountbytime/{id}")
    @ResponseBody
    public List<EchartsTimeVo> collectorselectCountbytime(@PathVariable("id") int id){
        List<EchartsTimeVo> result=recycleOrderService.collectorselectCountbytime(id);
        return result;
    }
    @GetMapping("/adminselectCountbytime")
    @ResponseBody
    public List<EchartsTimeVo> adminselectCountbytime(){
        List<EchartsTimeVo> result=recycleOrderService.adminselectCountbytime();
        return result;
    }
    @GetMapping("/userselectCountbytime/{id}")
    @ResponseBody
    public List<EchartsTimeVo> userselectCountbytime(@PathVariable("id") int id){
        List<EchartsTimeVo> result=recycleOrderService.userselectCountbytime(id);
        return result;
    }
    //
    //Echarts根据日期统计订单
    @GetMapping("/collectorselectCountbydate/{id}")
    @ResponseBody
    public List<EchartsTimeVo> collectorselectCountbydate(@PathVariable("id") int id){
        List<EchartsTimeVo> result=recycleOrderService.collectorselectCountbydate(id);
        return result;
    }
    @GetMapping("/adminselectCountbydate")
    @ResponseBody
    public List<EchartsTimeVo> adminselectCountbydate(){
        List<EchartsTimeVo> result=recycleOrderService.adminselectCountbydate();
        return result;
    }
    @GetMapping("/userselectCountbydate/{id}")
    @ResponseBody
    public List<EchartsTimeVo> userselectCountbydate(@PathVariable("id") int id){
        List<EchartsTimeVo> result=recycleOrderService.userselectCountbydate(id);
        return result;
    }
    //
    //Echarts根据日期统计回收金额
    @GetMapping("/collectorselectAmountbydate/{id}")
    @ResponseBody
    public List<EchartsTimeVo> collectorselectAmountbydate(@PathVariable("id") int id){
        List<EchartsTimeVo> result=recycleOrderService.collectorselectAmountbydate(id);
        return result;
    }
    @GetMapping("/adminselectAmountbydate")
    @ResponseBody
    public List<EchartsTimeVo> adminselectAmountbydate(){
        List<EchartsTimeVo> result=recycleOrderService.adminselectAmountbydate();
        return result;
    }

    @PostMapping("/adminfindAllOrders/{start}/{limit}")
    @ResponseBody
    public PageInfo<AllOrdersVo> adminfindAllOrders(@RequestBody AdminReqirueQuery a,@PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<AllOrdersVo> result = recycleOrderService.adminfindAllOrders(a.getUsername(),a.getCollectorname(),a.getDatebyday(),a.getDatebymonth(),a.getDatepick(),start,limit);
        return result;
    }

    @PostMapping("/adminfindAllDoingOrders/{start}/{limit}")
    @ResponseBody
    public PageInfo<AllDoingOrdersVo> adminfindAllDoingOrders(@RequestBody AdminReqirueQuery a,@PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<AllDoingOrdersVo> result = recycleOrderService.adminfindAllDoingOrders(a.getUsername(),a.getCollectorname(),a.getDatebyday(),a.getDatebymonth(),a.getDatepick(),start,limit);
        return result;
    }

    @GetMapping("/getallitem")
    @ResponseBody
    public List<ItemVo> getAllItem(){
        List<ItemVo> result=recycleOrderService.getAllItems();
        return result;

    }

    @PostMapping("/placeanorder")
    @ResponseBody
    public Boolean PlaceAnOrder(@RequestBody OrderDto order){

        Boolean result=recycleOrderService.placeAnOrder(order);
        return result;
    }

    @PostMapping("/updateanorder")
    @ResponseBody
    public Boolean updateAnOrder(@RequestBody OrderDto order){

        Boolean result=recycleOrderService.updateAnOrder(order);
        return result;
    }
    //查询某个订单
    @GetMapping("/getAnOrder/{id}")
    @ResponseBody
    public RecycleOrders findAnOrder(@PathVariable("id") int id){
        RecycleOrders recycleOrders=recycleOrderService.findAnOrder(id);
        return recycleOrders;
    }


    //给订单安排派送员
    @GetMapping("/placecollector/{recycleorderId}/{collectorName}")
    @ResponseBody
    public int placeCollector(@PathVariable("recycleorderId") int recycleorderId,@PathVariable("collectorName") String collectorName){
        boolean result=recycleOrderService.placecollector(recycleorderId,collectorName);
        if(result){
            return recycleorderId;
        }
        else{
            return 0;
        }
    }

    //派送员确认订单
    @PostMapping("/confirmorder")
    @ResponseBody
    public Boolean confirmOrder(@RequestBody OrderDto order){
        Boolean result=recycleOrderService.confirmOrder(order);
        return result;
    }


}
