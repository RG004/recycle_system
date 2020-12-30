package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.ItemTypeDao;
import com.example.recycle_system_springboot.pojo.entity.ItemType;
import com.example.recycle_system_springboot.service.ItemTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ItemTypeServiceImpl implements ItemTypeService {

    @Resource
    ItemTypeDao itemTypeDao;

    @Override
    public List<ItemType> selectAll() {
        List<ItemType> result= itemTypeDao.selectAll();
        return result;
    }

    @Override
    public boolean update(ItemType itemType) {
        itemTypeDao.updateByPrimaryKey(itemType);
        return true;
    }

    @Override
    public ItemType insert(ItemType itemType) {
        itemTypeDao.insert(itemType);
        ItemType result=itemTypeDao.selectByName(itemType.getItemTypeName());
        return result;
    }
}
