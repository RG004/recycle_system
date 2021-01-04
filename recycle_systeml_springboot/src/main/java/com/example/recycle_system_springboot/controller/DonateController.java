package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.dto.OrderDto;
import com.example.recycle_system_springboot.pojo.entity.Donation;
import com.example.recycle_system_springboot.service.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class DonateController {
    @Resource
    DonationService donationService;

    @PostMapping("/placeandonation")
    @ResponseBody
    public Boolean PlaceAnOrder(@RequestBody Donation order){
        Boolean result=donationService.placeAnDonation(order);
        return result;
    }
}
