package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.query.AddressQuery;
import com.example.recycle_system_springboot.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
public class AddressController {

    @Resource
    AddressService addressService;

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

    @PostMapping("/updateAddress/{addressId}/{addressDetails}")
    @ResponseBody
    public boolean updateAddress(@PathVariable("addressId") int addressId,@PathVariable("addressDetails") String addressDetails ){
        boolean result =addressService.updateAddress(addressId,addressDetails);
        return result;
    }

}
