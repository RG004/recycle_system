package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.AddressDao;
import com.example.recycle_system_springboot.service.AddressService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    AddressDao addressDao;

    @Override
    public boolean updateAddress(int addressId, String addressDetails) {
        addressDao.updateAddressDetail(addressId,addressDetails);
        return true;
    }
}
