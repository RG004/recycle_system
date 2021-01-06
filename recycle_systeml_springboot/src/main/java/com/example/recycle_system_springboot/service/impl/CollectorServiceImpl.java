package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.CollectorDao;
import com.example.recycle_system_springboot.pojo.dto.CollectorRegisterDto;
import com.example.recycle_system_springboot.pojo.entity.Collector;
import com.example.recycle_system_springboot.pojo.vo.CollectorVo;
import com.example.recycle_system_springboot.service.CollectorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollectorServiceImpl implements CollectorService {
    @Resource
    CollectorDao collectorDao;
    @Resource
    Collector collector;
    @Override
    public CollectorVo selctCollectorInfo(int collectorid) {
        CollectorVo collectorVo=collectorDao.selectRecyclesite(collectorid);
        return collectorVo;
    }

    @Override
    public boolean updatePhone(int id, String phone) {
        collectorDao.updatePhoneByPrimaykey(id,phone);
        return true;
    }

    @Override
    public List<Collector> selectAll() {
        List<Collector> result=collectorDao.selectAll();
        return result;
    }

    @Override
    public boolean registerCollector(int siteid, CollectorRegisterDto c) {
        collector.setSiteId(siteid);
        collector.setUserName(c.getUserName());
        collector.setCollectorName(c.getCollectorName());
        collector.setPassword(c.getPassword());
        collector.setPhone(c.getPhone());
        collector.setIdcardNumber(c.getIdcardNumber());
        collector.setUserStatus("F");
        if(collectorDao.selectByUserName(collector.getUserName())==null)
        {
            collectorDao.insert(collector);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean changeStatus(int id,String status) {
        collectorDao.updateStatusByPrimaykey(id,status);
        return true;
    }

    @Override
    public List<CollectorVo> selctAllCollector() {
        List<CollectorVo> result=collectorDao.selectAllCollector();
        return result;
    }
}
