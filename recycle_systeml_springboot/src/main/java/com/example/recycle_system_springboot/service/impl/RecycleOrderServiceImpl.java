package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.RecycleOrdersDao;
import com.example.recycle_system_springboot.pojo.vo.CollectorDoingOrdersVo;
import com.example.recycle_system_springboot.pojo.vo.CollectorOrdersVo;
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

    @Override
    public PageInfo<CollectorOrdersVo> collectorfindAllOrders(int id, int start, int limit) {
        PageHelper.startPage(start,limit);
        Page<CollectorOrdersVo> list = recycleOrdersDao.selectOrderByCollectorId(id);
        PageInfo<CollectorOrdersVo> result= new PageInfo<>(list);
        return result;
    }

    @Override
    public PageInfo<CollectorDoingOrdersVo> collectorfindDoingOrders(int id, int start, int limit) {
        PageHelper.startPage(start,limit);
        Page<CollectorDoingOrdersVo> list = recycleOrdersDao.selectDoingOrderByCollectorId(id);
        PageInfo<CollectorDoingOrdersVo> result= new PageInfo<>(list);
        return result;
    }

    @Override
    public PageInfo<RecycleOrdersVo> userfindOrdersByCollectorname(int id, String collectorname,String datebyday,String datebymonth,String datepick,int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<RecycleOrdersVo> list = recycleOrdersDao.selectOrderByCollectorname(id,collectorname,datebyday);
            PageInfo<RecycleOrdersVo> result= new PageInfo<>(list);
            return result;
        }else {
            Page<RecycleOrdersVo> list = recycleOrdersDao.selectOrderByCollectornameBymonth(id, collectorname, datebymonth);
            PageInfo<RecycleOrdersVo> result = new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<DoingOrdersVo> userfindDoingOrdersByCollectorname(int id, String collectorname, int start, int limit) {
        PageHelper.startPage(start,limit);
        Page<DoingOrdersVo> list = recycleOrdersDao.selectDoingOrderByCollectorname(id,collectorname);
        PageInfo<DoingOrdersVo> result= new PageInfo<>(list);
        return result;
    }

}
