package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.vo.ItemManageVo;
import com.example.recycle_system_springboot.service.ItemService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ItemController {

    @Resource
    ItemService itemService;

    @GetMapping("/allItem/{start}/{limit}")
    @ResponseBody
    public PageInfo<ItemManageVo> userfindAllOrders(@PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<ItemManageVo> result =itemService.selectAll(start,limit);
        return result;
    }
}
