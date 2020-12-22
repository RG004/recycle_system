package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.RecycleOrdersDao;
import com.example.recycle_system_springboot.pojo.vo.DoingOrdersVo;
import com.example.recycle_system_springboot.pojo.vo.RecycleOrdersVo;
import com.example.recycle_system_springboot.service.RecycleOrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

@Service
public class RecycleOrderServiceImpl implements RecycleOrderService {
    @Resource
    RecycleOrdersDao recycleOrdersDao;

    @Override
    public PageInfo<RecycleOrdersVo> userfindAllOrders(int id,int start,int limit) {
        PageHelper.startPage(start,limit);
        Page<RecycleOrdersVo> list = recycleOrdersDao.selectOrderByUserId(id);
        PageInfo<RecycleOrdersVo> result= new PageInfo<>(list);
        return result;
    }

    @Override
    public PageInfo<DoingOrdersVo> userfindDoingOrders(int id,int start,int limit) {
        PageHelper.startPage(start,limit);
        Page<DoingOrdersVo> list = recycleOrdersDao.selectDoingOrderByUserId(id);
        PageInfo<DoingOrdersVo> result= new PageInfo<>(list);
        return result;
    }
}
