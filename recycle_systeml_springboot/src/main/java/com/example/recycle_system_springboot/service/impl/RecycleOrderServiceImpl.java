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

    //得到所有的items
    @Override
    public List<ItemVo> getAllItems() {
        List<ItemVo> result=itemDao.getAllItems();
        return result;
    }

    @Override
    public Boolean placeAnOrder(OrderDto orderVo) {
        Boolean result=false;
        int i=recycleOrdersDao.insert(orderVo);System.out.println(i);
//        System.out.println(orderVo.getRecycleOrderId());

        recycleOrderDetail.setRecycleOrderId(orderVo.getRecycleOrderId());
        System.out.println(recycleOrderDetail);
        for(ItemVo itemVo:orderVo.getTableData()){
            for(Item item:itemVo.getItemsList()){
                if(item.getQuantity()>0.0){
                    recycleOrderDetail.setQuantity(item.getQuantity());
                    recycleOrderDetail.setItemId(item.getItemId());
                    int j=recycleOrderDetailDao.insert(recycleOrderDetail);
                    System.out.println(j);
                }
            }
        }

        return result;
    }

    @Override
    public Boolean updateAnOrder(OrderDto orderVo) {
        Boolean result=false;
        int i=recycleOrdersDao.updateByPrimaryKey(orderVo);
//        System.out.println(orderVo.getRecycleOrderId());
        recycleOrderDetailDao.deleteByRecycleOrderId(orderVo.getRecycleOrderId());
        recycleOrderDetail.setRecycleOrderId(orderVo.getRecycleOrderId());
        System.out.println(recycleOrderDetail);
        for(ItemVo itemVo:orderVo.getTableData()){
            for(Item item:itemVo.getItemsList()){
                if(item.getQuantity()>0.0){
                    recycleOrderDetail.setQuantity(item.getQuantity());
                    recycleOrderDetail.setItemId(item.getItemId());
                    int j=recycleOrderDetailDao.insert(recycleOrderDetail);
                    System.out.println(j);
                }
            }
        }

        return result;
    }

    //查找一个订单
    @Override
    public RecycleOrders findAnOrder(int id) {
        RecycleOrders recycleOrders=recycleOrdersDao.selectByPrimaryKey(id);
        return recycleOrders;
    }

}
