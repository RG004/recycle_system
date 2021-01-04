package com.example.recycle_system_springboot.pojo.vo;

import lombok.Data;

@Data
public class AllDoingOrdersVo {
    private Integer recycleOrderId;

    private String addressDetails;

    private String userName;

    private String collectorName;

    private String scheduledTime;
}
