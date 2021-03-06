package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.ItemDao;
import com.example.recycle_system_springboot.pojo.entity.Item;
import com.example.recycle_system_springboot.pojo.vo.ItemManageVo;
import com.example.recycle_system_springboot.service.ItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    ItemDao itemDao;

    @Override
    public PageInfo<ItemManageVo> selectAll(int start, int limit) {
        PageHelper.startPage(start,limit);
        Page<ItemManageVo> list = itemDao.selectAll();
        PageInfo<ItemManageVo> result= new PageInfo<>(list);
        return result;
    }

    @Override
    public Item insert(Item item){
        itemDao.insert(item);
        Item result = itemDao.selectByPrimaryKey(item.getItemId());
        return result;
    }

    @Override
    public int delete(Item item){

        int result=itemDao.deleteByPrimaryKey(item.getItemId());
        return result;
    }

    @Override
    public int update(Item item){
        int result=itemDao.updateByPrimaryKey(item);
        return result;
    }
}
