package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.UserDao;
import com.example.recycle_system_springboot.pojo.entity.User;
import com.example.recycle_system_springboot.service.RegisterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    UserDao userDao;

    @Override
    public boolean insertuser(User user) {
        User u=userDao.selectByUserName(user.getUserName());
        if(u!=null){
            return false;
        }
        else{
            userDao.insert(user);
            return true;
        }
    }
}
