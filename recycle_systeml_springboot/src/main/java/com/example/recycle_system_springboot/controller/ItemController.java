package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.entity.Item;
import com.example.recycle_system_springboot.pojo.vo.ItemVo;
import com.example.recycle_system_springboot.service.ItemService;
import com.example.recycle_system_springboot.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
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


    @PostMapping("/uploadimg")
    @ResponseBody
    public String coversUpload(MultipartFile file) throws Exception {
        String folder = "C:\\Users\\92045\\Desktop\\javaee\\课程设计\\recycle_system\\recycle_system_vue\\src\\assets\\imagebox";
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
