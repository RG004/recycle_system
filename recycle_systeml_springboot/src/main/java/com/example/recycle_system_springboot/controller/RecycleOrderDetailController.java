package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.vo.RecycleOrdersDetailVo;
import com.example.recycle_system_springboot.pojo.vo.RecycleOrdersVo;
import com.example.recycle_system_springboot.service.RecycleOrderDetailService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RecycleOrderDetailController {
    //查询订单详情
    @Resource
    RecycleOrderDetailService recycleOrderDetailService;

    @GetMapping("/OrdersDetail/{id}")
    @ResponseBody
    public List<RecycleOrdersDetailVo> findOrdersDetail(@PathVariable("id") int id){
        List<RecycleOrdersDetailVo> result = recycleOrderDetailService.findOrdersDetail(id);
        return result;
    }
}
