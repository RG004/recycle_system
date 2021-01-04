package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.entity.Item;
import com.example.recycle_system_springboot.pojo.vo.ItemVo;
import com.example.recycle_system_springboot.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ItemController {
    //查询废品
    @Resource
    ItemService itemService;

    @GetMapping("/Item")
    @ResponseBody
    public List<ItemVo> findAllItem(){
        List<ItemVo> result = itemService.findAllItem();
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
