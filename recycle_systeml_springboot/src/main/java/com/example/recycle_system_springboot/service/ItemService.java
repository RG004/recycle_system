package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.entity.Item;
import com.example.recycle_system_springboot.pojo.vo.ItemVo;

import java.util.List;

public interface ItemService {
    List<ItemVo> findAllItem();
    Item insert(Item item);
    int delete(Item item);
    int update(Item item);
}
