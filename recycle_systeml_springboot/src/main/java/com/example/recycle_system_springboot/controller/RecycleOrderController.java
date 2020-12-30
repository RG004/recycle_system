package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.dao.RecycleOrdersDao;
import com.example.recycle_system_springboot.pojo.dto.OrderDto;
import com.example.recycle_system_springboot.pojo.entity.RecycleOrders;
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


    @Resource
    RecycleOrdersDao recycleOrdersDao;

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

    //下单控制
    @GetMapping("/getallitem")
    @ResponseBody
    public List<ItemVo> getAllItem(){
        List<ItemVo> result=recycleOrderService.getAllItems();
        return result;

    }

    @PostMapping("/placeanorder")
    @ResponseBody
    public Boolean PlaceAnOrder(@RequestBody OrderDto order){
        System.out.println(order);
        Boolean result=recycleOrderService.placeAnOrder(order);
        return result;
    }
    //修改订单
    @PostMapping("/updateanorder")
    @ResponseBody
    public Boolean updateAnOrder(@RequestBody OrderDto order){
        System.out.println(order);
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

}
