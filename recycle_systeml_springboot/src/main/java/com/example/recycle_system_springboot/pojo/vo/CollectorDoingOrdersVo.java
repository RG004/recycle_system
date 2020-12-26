package com.example.recycle_system_springboot.pojo.vo;

import lombok.Data;

@Data
public class CollectorDoingOrdersVo {
    private Integer recycleOrderId;

    private String addressDetails;

    private String userName;

    private String scheduledTime;

    private String phone;

}
