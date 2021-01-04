package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.entity.User;
import com.example.recycle_system_springboot.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class RegisterController {
    //注册
    @Resource
    RegisterService registerService;
    @PostMapping("/register/user")
    @ResponseBody
    public Boolean Register(@RequestBody User user){
        Boolean ifSuccess=registerService.registeruser(user);
            return ifSuccess;
    }
}
