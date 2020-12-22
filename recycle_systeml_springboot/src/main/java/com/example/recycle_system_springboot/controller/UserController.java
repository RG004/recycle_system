package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.dao.UserDao;
import com.example.recycle_system_springboot.pojo.vo.AddressVo;
import com.example.recycle_system_springboot.pojo.vo.UserVo;
import com.example.recycle_system_springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {
    //用于查询用户的信息、修改用户的手机号
    @Resource
    UserService userService;

    @GetMapping("/userAlladdress/{id}")
    @ResponseBody
    public UserVo userfindAlladdress(@PathVariable("id") int id){//查找
        UserVo userVo = userService.userfindAlladdress(id);
        return userVo;
    }

    @PostMapping("/userupdatePhone/{id}/{phone}")
    @ResponseBody
    public boolean updatePhone(@PathVariable("id") int id,@PathVariable("phone") String phone){
        boolean result=userService.updatePhone(id,phone);
        return result;
    }

}
