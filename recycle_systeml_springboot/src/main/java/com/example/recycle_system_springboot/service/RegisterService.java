package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.entity.Admin;
import com.example.recycle_system_springboot.pojo.entity.Collector;
import com.example.recycle_system_springboot.pojo.entity.User;

public interface RegisterService {
    Boolean registeruser(User user);
    Boolean registeradmin(Admin admin);
    Boolean registercollector(Collector collector);
}
