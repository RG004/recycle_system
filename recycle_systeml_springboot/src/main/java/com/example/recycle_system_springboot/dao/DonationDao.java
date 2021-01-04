package com.example.recycle_system_springboot.dao;

import com.example.recycle_system_springboot.pojo.entity.Donation;

public interface DonationDao {
    int deleteByPrimaryKey(Integer donateId);

    int insert(Donation record);

    int insertSelective(Donation record);

    Donation selectByPrimaryKey(Integer donateId);

    int updateByPrimaryKeySelective(Donation record);

    int updateByPrimaryKey(Donation record);
}