package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.AdminDao;
import com.example.recycle_system_springboot.dao.CollectorDao;
import com.example.recycle_system_springboot.dao.UserDao;
import com.example.recycle_system_springboot.pojo.vo.LoginMsg;
import com.example.recycle_system_springboot.pojo.entity.Admin;
import com.example.recycle_system_springboot.pojo.entity.Collector;
import com.example.recycle_system_springboot.pojo.entity.User;
import com.example.recycle_system_springboot.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    UserDao userDao;
    @Resource
    AdminDao adminDao;
    @Resource
    CollectorDao collectorDao;

    @Override
    public LoginMsg CheckLogin(int identity, String username, String password) {
        LoginMsg msg = new LoginMsg();
        if(identity==1){
            User user=userDao.selectByUserName(username);
            if(user.getPassword().equals(password)){
                msg.setId(user.getUserId());
                msg.setIdentity(identity);
                msg.setName(user.getUserName());
                msg.setLogin(true);
            }
            else {
                msg.setLogin(false);
            }
        }
        else if(identity==2){
            Collector collector = collectorDao.selectByUserName(username);
            if (collector.getPassword().equals(password)) {
                msg.setId(collector.getCollectorPersonId());
                msg.setIdentity(identity);
                msg.setName(collector.getUserName());
                msg.setLogin(true);
            } else {
                msg.setLogin(false);
            }
        }
        else if(identity==3) {
            Admin admin=adminDao.selectByUserName(username);
            if(admin.getPassword().equals(password)){
                msg.setIdentity(identity);
                msg.setId(admin.getAdminId());
                msg.setName(admin.getUserName());
                msg.setLogin(true);
            }else {
                msg.setLogin(false);
            }
        }
        return msg;
    }
}
