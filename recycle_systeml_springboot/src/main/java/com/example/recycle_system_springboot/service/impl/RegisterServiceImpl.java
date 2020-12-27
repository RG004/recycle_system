package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.AdminDao;
import com.example.recycle_system_springboot.dao.CollectorDao;
import com.example.recycle_system_springboot.dao.UserDao;
import com.example.recycle_system_springboot.pojo.entity.Admin;
import com.example.recycle_system_springboot.pojo.entity.Collector;
import com.example.recycle_system_springboot.pojo.entity.User;
import com.example.recycle_system_springboot.service.RegisterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Resource
    UserDao userDao;
    @Resource
    AdminDao adminDao;
    @Resource
    CollectorDao collectorDao;


    @Override
    public Boolean registeruser(User user) {
        System.out.println(user);
        User u2=userDao.selectByUserName(user.getUserName());
        if(u2!=null)return false;

        List<User> allUser=userDao.selectAllUser();
        int id=1;
        for( User u:allUser){
            if(id==u.getUserId()){
                id++;
            }
            else break;
        }
        user.setUserId(id);
        Integer n=userDao.insert(user);
        if(n==0)return false;
        else return true;
    }

    @Override
    public Boolean registeradmin(Admin admin) {
        return null;
    }

    @Override
    public Boolean registercollector(Collector collector) {
        return null;
    }
}
