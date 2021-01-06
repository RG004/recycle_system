package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.*;
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
    CollectorDao collectorDao;
    @Resource
    ItemDao itemDao;
    @Resource
    RecycleOrderDetail recycleOrderDetail;
    @Resource
    EvaluationDao evaluationDao;
    @Resource
    RecycleOrders recycleOrders;
    @Override
    public PageInfo<OrdersVo> userfindAllOrders(int userid, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<OrdersVo> list =recycleOrdersDao.selectAllByuser(userid,collectorname,datebyday,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<OrdersVo> list =recycleOrdersDao.selectAllByuser(userid,collectorname,datebymonth,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<OrdersVo> userfindAllDoingOrders(int userid, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<OrdersVo> list =recycleOrdersDao.selectDoingByuser(userid,collectorname,datebyday,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<OrdersVo> list =recycleOrdersDao.selectDoingByuser(userid,collectorname,datebymonth,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<OrdersVo> collectorfindAllOrders(int collectorid, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<OrdersVo> list =recycleOrdersDao.selectAllBycollector(collectorid,collectorname,datebyday,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<OrdersVo> list =recycleOrdersDao.selectAllBycollector(collectorid,collectorname,datebymonth,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<OrdersVo> collectorfindAllDoingOrders(int collectorid, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<OrdersVo> list =recycleOrdersDao.selectDoingBycollector(collectorid,collectorname,datebyday,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<OrdersVo> list =recycleOrdersDao.selectDoingBycollector(collectorid,collectorname,datebymonth,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<OrdersVo> adminfindAllOrders(String username, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<OrdersVo> list =recycleOrdersDao.selectAllByadmin(username,collectorname,datebyday,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<OrdersVo> list =recycleOrdersDao.selectAllByadmin(username,collectorname,datebymonth,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<OrdersVo> adminfindAllDoingOrders(String username, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<OrdersVo> list =recycleOrdersDao.selectDoingByadmin(username,collectorname,datebyday,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<OrdersVo> list =recycleOrdersDao.selectDoingByadmin(username,collectorname,datebymonth,datepick);
            PageInfo<OrdersVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public List<EchartsTimeVo> userselectCountbytime(int id){
        List<EchartsTimeVo> list=recycleOrdersDao.userselectCountbytime(id);
        return list;
    }

    @Override
    public List<EchartsTimeVo> userselectCountbydate(int id){
        List<EchartsTimeVo> list=recycleOrdersDao.userselectCountbydate(id);
        return list;
    }

    @Override
    public List<EchartsTimeVo> adminselectCountbydate(){
        List<EchartsTimeVo> list=recycleOrdersDao.adminselectCountbydate();
        return list;
    }

    @Override
    public List<EchartsTimeVo> adminselectCountbytime(){
        List<EchartsTimeVo> list=recycleOrdersDao.adminselectCountbytime();
        return list;
    }

    @Override
    public List<EchartsTimeVo> adminselectAmountbydate(){
        List<EchartsTimeVo> list=recycleOrdersDao.adminselectAmountbydate();
        return list;
    }

    @Override
    public List<EchartsTimeVo> collectorselectCountbydate(int collectorid){
        List<EchartsTimeVo> list=recycleOrdersDao.collectorselectCountbydate(collectorid);
        return list;
    }

    @Override
    public List<EchartsTimeVo> collectorselectCountbytime(int collectorid){
        List<EchartsTimeVo> list=recycleOrdersDao.collectorselectCountbytime(collectorid);
        return list;
    }

    @Override
    public List<EchartsTimeVo> collectorselectAmountbydate(int collectorid){
        List<EchartsTimeVo> list=recycleOrdersDao.collectorselectAmountbydate(collectorid);
        return list;
    }

    @Override
    public List<ItemVo> getAllItems() {
        List<ItemVo> result=itemDao.getAllItems();
        return result;
    }

    @Override
    public Boolean placeAnOrder(OrderDto orderVo) {
        Double amount=0.0;
        int i=recycleOrdersDao.insert(orderVo);
        recycleOrderDetail.setRecycleOrderId(orderVo.getRecycleOrderId());
        for(ItemVo itemVo:orderVo.getTableData()){
            for(ItemRecycleVo item:itemVo.getItemsList()){
                if(item.getQuantity()>0.0){
                    recycleOrderDetail.setQuantity(item.getQuantity());
                    recycleOrderDetail.setItemId(item.getItemId());
                    int j=recycleOrderDetailDao.insert(recycleOrderDetail);
                    amount+=(double)item.getItemPrice()*item.getQuantity();
                }
            }
        }
        orderVo.setTotalAmount(amount);
        recycleOrdersDao.updateByPrimaryKeySelective(orderVo);
        return true;
    }

    @Override
    public Boolean updateAnOrder(OrderDto orderVo) {
        Double amount=0.0;
        int i=recycleOrdersDao.updateByPrimaryKey(orderVo);
        recycleOrderDetailDao.deleteByRecycleOrderId(orderVo.getRecycleOrderId());
        recycleOrderDetail.setRecycleOrderId(orderVo.getRecycleOrderId());
        for(ItemVo itemVo:orderVo.getTableData()){
            for(ItemRecycleVo item:itemVo.getItemsList()){
                if(item.getQuantity()>0.0){
                    recycleOrderDetail.setQuantity(item.getQuantity());
                    recycleOrderDetail.setItemId(item.getItemId());
                    int j=recycleOrderDetailDao.insert(recycleOrderDetail);
                    amount+=(double)item.getItemPrice()*item.getQuantity();
                }
            }
        }
        orderVo.setTotalAmount(amount);
        recycleOrdersDao.updateByPrimaryKeySelective(orderVo);
        return true;
    }

    @Override
    public RecycleOrders findAnOrder(int id) {
        RecycleOrders recycleOrders=recycleOrdersDao.selectByPrimaryKey(id);
        return recycleOrders;
    }

    @Override
    public Boolean placecollector(int recycleorderId, String collectorname) {
        int collectorId=collectorDao.selectBycollectorname(collectorname);
        recycleOrdersDao.updateCollector(recycleorderId,collectorId);
        return true;
    }

    @Override
    public Boolean confirmOrder(OrderDto orderVo) {
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
        return true;
    }

    @Override
    public Boolean Evaluateorder(EvaluationVo order) {
        evaluationDao.insert(order);
        recycleOrders.setRecycleOrderId(order.getRecycleOrderId());
        recycleOrders.setEvaluationId(order.getEvaluationId());
        recycleOrdersDao.updateByPrimaryKeySelective(recycleOrders);
        return true;
    }

    @Override
    public Boolean DeleteOrder(int recycleorderId) {
        RecycleOrders recycleOrders=recycleOrdersDao.selectByPrimaryKey(recycleorderId);
        recycleOrdersDao.deleteByPrimaryKey(recycleorderId);
        evaluationDao.deleteByPrimaryKey(recycleOrders.getEvaluationId());
        return true;
    }
}
