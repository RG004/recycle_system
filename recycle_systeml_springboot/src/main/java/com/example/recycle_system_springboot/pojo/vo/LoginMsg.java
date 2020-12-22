package com.example.recycle_system_springboot.pojo.vo;

import lombok.Data;

@Data
public class LoginMsg {
    //返回给前端关于登录的信息，封装成了一个类
    private int identity;
    private int id;
    private String name;
    private boolean login;
}
