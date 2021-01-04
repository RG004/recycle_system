package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.DonationDao;
import com.example.recycle_system_springboot.pojo.entity.Donation;
import com.example.recycle_system_springboot.service.DonationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DonationServiceImp implements DonationService {
    @Resource
    DonationDao donationDao;

    @Override
    public Boolean placeAnDonation(Donation order) {
        Boolean result=false;
        int i=donationDao.insert(order);
        if(i>0){
            result=true;
        }
        return result;
    }
}
