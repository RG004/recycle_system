package com.example.recycle_system_springboot.pojo.vo;

import lombok.Data;

@Data
public class CollectorOrdersVo {
    private Integer recycleOrderId;

    private String addressDetails;

    private String userName;

    private String scheduledTime;

    private String finishedTime;

    private String phone;
}
