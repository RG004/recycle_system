package com.example.recycle_system_springboot.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RecycleOrdersVo  {
    //返回给前端所有订单信息类
    private Integer recycleOrderId;

    private String addressDetails;

    private String collectorName;

    private String scheduledTime;

    private String finishedTime;

    private String phone;
}
