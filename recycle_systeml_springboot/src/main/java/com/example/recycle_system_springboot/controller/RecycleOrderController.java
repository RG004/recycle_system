package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.dao.RecycleOrdersDao;
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


    @PostMapping("/userfindAllOrders/{start}/{limit}")
    @ResponseBody
    public PageInfo<OrdersVo> userfindAllOrders(@RequestBody UserRequireQuery u,@PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<OrdersVo> result = recycleOrderService.userfindAllOrders(u.getId(),u.getCollectorname(),u.getDatebyday(),u.getDatebymonth(),u.getDatepick(),start,limit);
        return result;
    }

    @PostMapping("/userfindAllDoingOrders/{start}/{limit}")
    @ResponseBody
    public PageInfo<OrdersVo> userfindAllDoingOrders(@RequestBody UserRequireQuery u,@PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<OrdersVo> result = recycleOrderService.userfindAllDoingOrders(u.getId(),u.getCollectorname(),u.getDatebyday(),u.getDatebymonth(),u.getDatepick(),start,limit);
        return result;
    }

    @PostMapping("/collectorfindAllOrders/{start}/{limit}")
    @ResponseBody
    public PageInfo<OrdersVo> collectorfindAllOrders(@RequestBody CollectorRequireQuery c,@PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<OrdersVo> result = recycleOrderService.collectorfindAllOrders(c.getId(),c.getUsername(),c.getDatebyday(),c.getDatebymonth(),c.getDatepick(),start,limit);
        return result;
    }

    @PostMapping("/collectorfindAllDoingOrders/{start}/{limit}")
    @ResponseBody
    public PageInfo<OrdersVo> collectorfindAllDoingOrders(@RequestBody CollectorRequireQuery c,@PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<OrdersVo> result = recycleOrderService.collectorfindAllDoingOrders(c.getId(),c.getUsername(),c.getDatebyday(),c.getDatebymonth(),c.getDatepick(),start,limit);
        return result;
    }

    @PostMapping("/adminfindAllOrders/{start}/{limit}")
    @ResponseBody
    public PageInfo<OrdersVo> adminfindAllOrders(@RequestBody AdminReqirueQuery a,@PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<OrdersVo> result = recycleOrderService.adminfindAllOrders(a.getUsername(),a.getCollectorname(),a.getDatebyday(),a.getDatebymonth(),a.getDatepick(),start,limit);
        return result;
    }

    @PostMapping("/adminfindAllDoingOrders/{start}/{limit}")
    @ResponseBody
    public PageInfo<OrdersVo> adminfindAllDoingOrders(@RequestBody AdminReqirueQuery a,@PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<OrdersVo> result = recycleOrderService.adminfindAllDoingOrders(a.getUsername(),a.getCollectorname(),a.getDatebyday(),a.getDatebymonth(),a.getDatepick(),start,limit);
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

    @PostMapping("/evaluationorder")
    @ResponseBody
    public Boolean Evaluationdonation(@RequestBody EvaluationVo order){
        Boolean result=recycleOrderService.Evaluateorder(order);
        return result;
    }

    //删除订单
    @GetMapping("/userdeleteorder/{recycleorderId}")
    @ResponseBody
    public Boolean DeleteOrder(@PathVariable("recycleorderId") int recycleorderId){
        Boolean result=recycleOrderService.DeleteOrder(recycleorderId);
        return true;
    }
}
