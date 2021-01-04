package com.example.recycle_system_springboot.pojo.vo;

import lombok.Data;

@Data
public class DonationVo {
    private Integer donateId;

    private Integer userName;

    private Integer addressId;

    private String collectorName;

    private String scheduledTime;

    private String finishedTime;

    private String donateDetail;

}
