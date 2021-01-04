package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.dao.RecycleOrdersDao;
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

    @GetMapping("/echartsBytime/{id}")
    @ResponseBody
    public List<EchartsTimeVo> echartsBytime(@PathVariable("id") int id){
        List<EchartsTimeVo> result=recycleOrderService.echartsBytime(id);
        return result;
    }
    @GetMapping("/echartsBydate/{id}")
    @ResponseBody
    public List<EchartsTimeVo> echartsBydate(@PathVariable("id") int id){
        List<EchartsTimeVo> result=recycleOrderService.echartsBydate(id);
        return result;
    }
}
