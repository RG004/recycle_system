package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.vo.UserVo;

public interface UserService {
    UserVo userfindAlladdress(int id);

    boolean updatePhone(int id,String phone);
}
