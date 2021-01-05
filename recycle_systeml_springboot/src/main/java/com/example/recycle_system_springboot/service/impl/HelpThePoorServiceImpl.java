package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.HelpThePoorDao;
import com.example.recycle_system_springboot.pojo.entity.HelpThePoor;
import com.example.recycle_system_springboot.service.HelpThePoorService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HelpThePoorServiceImpl implements HelpThePoorService {

    @Resource
    HelpThePoorDao helpThePoorDao;

    @Override
    public PageInfo<HelpThePoor> selectAll(int start, int limit) {
        PageHelper.startPage(start,limit);
        Page<HelpThePoor> list= helpThePoorDao.selectAll();
        PageInfo<HelpThePoor> result =new PageInfo<>(list);
        return result;
    }

    @Override
    public boolean update(HelpThePoor helpThePoor) {
        helpThePoorDao.updateByPrimaryKey(helpThePoor);
        return true;
    }

    @Override
    public boolean delete(int id) {
        helpThePoorDao.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean insert(HelpThePoor helpThePoor) {
        helpThePoorDao.insert(helpThePoor);
        return true;
    }
}
