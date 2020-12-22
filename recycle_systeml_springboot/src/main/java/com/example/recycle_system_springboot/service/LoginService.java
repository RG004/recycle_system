package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.vo.LoginMsg;

public interface LoginService {
    LoginMsg CheckLogin(int identity,String username,String password);
}
