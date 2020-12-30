package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.vo.CollectorVo;
import com.example.recycle_system_springboot.pojo.vo.UserVo;
import com.example.recycle_system_springboot.service.CollectorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class CollectorController {
    @Resource
    CollectorService collectorService;

    @GetMapping("/collectorInfo/{id}")
    @ResponseBody
    public CollectorVo userfindAlladdress(@PathVariable("id") int id){//查找
        CollectorVo collectorVo = collectorService.selctCollectorInfo(id);
        return collectorVo;
    }

    @PostMapping("/collectorupdatePhone/{id}/{phone}")
    @ResponseBody
    public boolean updatePhone(@PathVariable("id") int id,@PathVariable("phone") String phone){
        boolean result=collectorService.updatePhone(id,phone);
        return result;
    }

}
