package com.example.recycle_system_springboot.dao;

import com.example.recycle_system_springboot.pojo.entity.Item;

public interface ItemDao {
    int deleteByPrimaryKey(Integer itemId);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);
}