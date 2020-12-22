package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.UserDao;
import com.example.recycle_system_springboot.pojo.vo.UserVo;
import com.example.recycle_system_springboot.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public UserVo userfindAlladdress(int id) {
        UserVo userVo = userDao.selectAddress(id);
        return userVo;
    }

    @Override
    public boolean updatePhone(int id, String phone) {
        userDao.updatePhoneByPrimaryKey(id,phone);
        return true;
    }
}
