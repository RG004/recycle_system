package com.example.recycle_system_springboot.dao;

import com.example.recycle_system_springboot.pojo.entity.Collector;
import com.example.recycle_system_springboot.pojo.entity.User;

public interface CollectorDao {
    int deleteByPrimaryKey(Integer collectorPersonId);

    int insert(Collector record);

    int insertSelective(Collector record);

    Collector selectByPrimaryKey(Integer collectorPersonId);

    int updateByPrimaryKeySelective(Collector record);

    int updateByPrimaryKey(Collector record);

    Collector selectByUserName(String username);
}
