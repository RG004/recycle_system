package com.example.recycle_system_springboot.dao;

import com.example.recycle_system_springboot.pojo.entity.Item;
import com.example.recycle_system_springboot.pojo.vo.ItemVo;

import java.util.List;

public interface ItemDao {
    int deleteByPrimaryKey(Integer itemId);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    List<ItemVo> findAll();
}
