package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.RecycleOrderDetailDao;
import com.example.recycle_system_springboot.pojo.vo.RecycleOrdersDetailVo;
import com.example.recycle_system_springboot.service.RecycleOrderDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecycleOrderDetailServiceImpl implements RecycleOrderDetailService {
    @Resource
    RecycleOrderDetailDao recycleOrderDetailDao;

    @Override
    public List<RecycleOrdersDetailVo> findOrdersDetail(int id) {
        List<RecycleOrdersDetailVo> result = recycleOrderDetailDao.selectDetailByRecycleOrderId(id);
        return result;
    }
}
