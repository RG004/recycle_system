package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.query.AddressQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AddressController {

    @PostMapping("/insertAddress")
    @ResponseBody
    public String insertAddress(@RequestBody AddressQuery a){
        System.out.println(a);
        String result = "";
        for(String i:a.getValue()){
            result+=i;
        }
        String detail = a.getDetail();
        result+=detail;
        System.out.println(result);
        return  null;
    }
}
