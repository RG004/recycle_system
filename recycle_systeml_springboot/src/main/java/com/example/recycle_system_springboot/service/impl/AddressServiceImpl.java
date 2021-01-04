package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.AddressDao;
import com.example.recycle_system_springboot.pojo.entity.Address;
import com.example.recycle_system_springboot.service.AddressService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    AddressDao addressDao;

    @Override
    public boolean updateAddress(Address address) {
        addressDao.updateByPrimaryKey(address);
        return true;
    }

    @Override
    public boolean userdeleteAddress(int addressId) {
        addressDao.deleteAddressByUser(addressId);
        return true;
    }

    @Override
    public boolean insert(Address address) {
        addressDao.insert(address);
        return true;
    }


}
