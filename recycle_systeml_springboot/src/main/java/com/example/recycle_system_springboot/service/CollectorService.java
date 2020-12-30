package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.entity.ItemType;
import com.example.recycle_system_springboot.pojo.vo.CollectorVo;

public interface CollectorService {
    CollectorVo selctCollectorInfo(int collectorid);

    boolean updatePhone(int id,String phone);

}
