package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.HelpThePoorDao;
import com.example.recycle_system_springboot.dao.UserDao;
import com.example.recycle_system_springboot.pojo.entity.HelpThePoor;
import com.example.recycle_system_springboot.pojo.vo.UserVo;
import com.example.recycle_system_springboot.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;
    @Resource
    HelpThePoorDao helpThePoorDao;

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

    @Override
    public List<HelpThePoor> userfindAllHelp() {
        List<HelpThePoor> list=helpThePoorDao.selectAllHelp();
        return list;
    }
}
