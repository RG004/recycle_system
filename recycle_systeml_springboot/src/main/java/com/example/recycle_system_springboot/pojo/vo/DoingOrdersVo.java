package com.example.recycle_system_springboot.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DoingOrdersVo {
    //返回给前端的正在进行中的订单信息类
    private Integer recycleOrderId;

    private String addressDetails;

    private String collectorName;

    private String scheduledTime;

    private String phone;

}
