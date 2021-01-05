package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.CollectorDao;
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
}
