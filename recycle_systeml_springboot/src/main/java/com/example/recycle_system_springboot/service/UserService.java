package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.entity.HelpThePoor;
import com.example.recycle_system_springboot.pojo.vo.UserVo;

import java.util.List;

public interface UserService {
    UserVo userfindAlladdress(int id);

    boolean updatePhone(int id,String phone);

    List<HelpThePoor> userfindAllHelp();
}
