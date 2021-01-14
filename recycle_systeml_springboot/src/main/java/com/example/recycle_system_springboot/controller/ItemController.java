package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.entity.Item;
import com.example.recycle_system_springboot.pojo.vo.ItemManageVo;
import com.example.recycle_system_springboot.service.ItemService;
import com.example.recycle_system_springboot.utils.StringUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Controller
public class ItemController {

    @Resource
    ItemService itemService;

    @GetMapping("/allItem/{start}/{limit}")
    @ResponseBody
    public PageInfo<ItemManageVo> findAllItems(@PathVariable("start") int start, @PathVariable("limit") int limit){
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

    @PostMapping("/uploadimg")
    @ResponseBody
    public String coversUpload(MultipartFile file) throws Exception {
        String folder = "D:\\学习\\软件工程\\Git\\recycle_system\\recycle_system_vue\\src\\assets\\imagebox";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8181/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
