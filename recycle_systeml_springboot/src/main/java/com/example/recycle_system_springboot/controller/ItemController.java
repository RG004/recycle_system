package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.entity.Item;
import com.example.recycle_system_springboot.pojo.vo.ItemManageVo;
import com.example.recycle_system_springboot.service.ItemService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/insertItem")
    @ResponseBody
    public Item insert(@RequestBody Item item){
        Item result=itemService.insert(item);
        return  result;
    }
    @PostMapping("/deleteItem")
    @ResponseBody
    public int delete(@RequestBody Item item){
        int result=itemService.delete(item);
        return  result;
    }
    @PostMapping("/updateItem")
    @ResponseBody
    public int update(@RequestBody Item item){
        int result=itemService.update(item);
        return result;
    }
}
