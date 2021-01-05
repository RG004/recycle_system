package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.entity.Item;
import com.example.recycle_system_springboot.pojo.vo.ItemManageVo;
import com.github.pagehelper.PageInfo;

public interface ItemService {
    PageInfo<ItemManageVo> selectAll(int start, int limit);

    Item insert(Item item);

    int delete(Item item);

    int update(Item item);
}
