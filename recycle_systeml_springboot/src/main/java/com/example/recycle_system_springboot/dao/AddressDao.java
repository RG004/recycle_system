package com.example.recycle_system_springboot.dao;

import com.example.recycle_system_springboot.pojo.entity.Address;
import com.example.recycle_system_springboot.pojo.vo.AddressVo;

import java.util.List;

public interface AddressDao {
    int deleteByPrimaryKey(Integer addressId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    List<AddressVo> selectByUserID(int userid);
}
