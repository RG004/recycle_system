package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.dao.RecycleOrdersDao;
import com.example.recycle_system_springboot.pojo.vo.DoingOrdersVo;
import com.example.recycle_system_springboot.pojo.vo.RecycleOrdersVo;
import com.example.recycle_system_springboot.service.RecycleOrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/userFindordersBycellectorname/{id}/{cellectorname}/{start}/{limit}")
    @ResponseBody
    public PageInfo<RecycleOrdersVo> selectOrderBycellector(@PathVariable("id") int id,@PathVariable("cellectorname") String cellectorname,@PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<RecycleOrdersVo> result = recycleOrderService.userfindOrdersBycellector(id,cellectorname,start,limit);
        return result;
    }

    @GetMapping("/userDayorders/{id}/{start}/{limit}/{day}")
    @ResponseBody
    public PageInfo<RecycleOrdersVo> userfindDayOrders(@PathVariable("id") int id,@PathVariable("start") int start, @PathVariable("limit") int limit,@PathVariable("day") String day){
        PageInfo<RecycleOrdersVo> result =recycleOrderService.userfindDayOrders(id,start,limit,day);
        return result;
    }
    @GetMapping("/userMonthorders/{id}/{start}/{limit}/{month}")

    @ResponseBody
    public PageInfo<RecycleOrdersVo> userfindMonthOrders(@PathVariable("id") int id,@PathVariable("start") int start, @PathVariable("limit") int limit,@PathVariable("month") String month){
        PageInfo<RecycleOrdersVo> result =recycleOrderService.userfindMonthOrders(id,start,limit,month);
        return result;
    }


}
