package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.ItemDao;
import com.example.recycle_system_springboot.pojo.entity.Item;
import com.example.recycle_system_springboot.pojo.vo.ItemVo;
import com.example.recycle_system_springboot.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    ItemDao itemDao;

    @Override
    public List<ItemVo> findAllItem(){
        List<ItemVo> result = itemDao.findAll();
        return  result;
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
