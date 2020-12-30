package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.vo.ItemManageVo;
import com.github.pagehelper.PageInfo;

public interface ItemService {
    PageInfo<ItemManageVo> selectAll(int start, int limit);
}
