package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.ItemDao;
import com.example.recycle_system_springboot.dao.RecycleOrderDetailDao;
import com.example.recycle_system_springboot.dao.RecycleOrdersDao;
import com.example.recycle_system_springboot.pojo.dto.OrderDto;
import com.example.recycle_system_springboot.pojo.entity.Item;
import com.example.recycle_system_springboot.pojo.entity.RecycleOrderDetail;
import com.example.recycle_system_springboot.pojo.entity.RecycleOrders;
import com.example.recycle_system_springboot.pojo.vo.*;
import com.example.recycle_system_springboot.service.RecycleOrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecycleOrderServiceImpl implements RecycleOrderService {
    @Resource
    RecycleOrdersDao recycleOrdersDao;
    @Resource
    RecycleOrderDetailDao recycleOrderDetailDao;
    @Resource
    ItemDao itemDao;
    @Resource
    RecycleOrderDetail recycleOrderDetail;

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

    @Override
    public PageInfo<CollectorOrdersVo> collectorfindOrdersByUsername(int id, String username, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<CollectorOrdersVo> list = recycleOrdersDao.CollectorselctOrderByUsername(id,username,datebyday);
            PageInfo<CollectorOrdersVo> result= new PageInfo<>(list);
            return result;
        }else {
            Page<CollectorOrdersVo> list = recycleOrdersDao.CollectorselctOrderByUsernameBymonth(id, username, datebymonth);
            PageInfo<CollectorOrdersVo> result = new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<CollectorDoingOrdersVo> collectorfindDoingOrdersByUsername(int id, String username, int start, int limit) {
        PageHelper.startPage(start,limit);
        Page<CollectorDoingOrdersVo> list = recycleOrdersDao.CollectorselectDoingOrderByUsername(id,username);
        PageInfo<CollectorDoingOrdersVo> result= new PageInfo<>(list);
        return result;
    }

    @Override
    public PageInfo<AllOrdersVo> adminfindAllOrders(String username, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<AllOrdersVo> list =recycleOrdersDao.selectAllByadmin(username,collectorname,datebyday,datepick);
            PageInfo<AllOrdersVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<AllOrdersVo> list =recycleOrdersDao.selectAllByadmin(username,collectorname,datebymonth,datepick);
            PageInfo<AllOrdersVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<AllDoingOrdersVo> adminfindAllDoingOrders(String username, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<AllDoingOrdersVo> list =recycleOrdersDao.selectDoingByadmin(username,collectorname,datebyday,datepick);
            PageInfo<AllDoingOrdersVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<AllDoingOrdersVo> list =recycleOrdersDao.selectDoingByadmin(username,collectorname,datebymonth,datepick);
            PageInfo<AllDoingOrdersVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public List<ItemVo> getAllItems() {
        List<ItemVo> result=itemDao.getAllItems();
        return result;
    }

    @Override
    public Boolean placeAnOrder(OrderDto orderVo) {
        Boolean result=false;
        int i=recycleOrdersDao.insert(orderVo);
        recycleOrderDetail.setRecycleOrderId(orderVo.getRecycleOrderId());
        for(ItemVo itemVo:orderVo.getTableData()){
            for(ItemRecycleVo item:itemVo.getItemsList()){
                if(item.getQuantity()>0.0){
                    recycleOrderDetail.setQuantity(item.getQuantity());
                    recycleOrderDetail.setItemId(item.getItemId());
                    int j=recycleOrderDetailDao.insert(recycleOrderDetail);
                }
            }
        }
        return result;
    }

    @Override
    public Boolean updateAnOrder(OrderDto orderVo) {
        Boolean result=false;
        int i=recycleOrdersDao.updateByPrimaryKey(orderVo);
        recycleOrderDetailDao.deleteByRecycleOrderId(orderVo.getRecycleOrderId());
        recycleOrderDetail.setRecycleOrderId(orderVo.getRecycleOrderId());
        for(ItemVo itemVo:orderVo.getTableData()){
            for(ItemRecycleVo item:itemVo.getItemsList()){
                if(item.getQuantity()>0.0){
                    recycleOrderDetail.setQuantity(item.getQuantity());
                    recycleOrderDetail.setItemId(item.getItemId());
                    int j=recycleOrderDetailDao.insert(recycleOrderDetail);
                }
            }
        }

        return result;
    }

    @Override
    public RecycleOrders findAnOrder(int id) {
        RecycleOrders recycleOrders=recycleOrdersDao.selectByPrimaryKey(id);
        return recycleOrders;
    }

}
