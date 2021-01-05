package com.example.recycle_system_springboot.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * donation
 * @author
 */
@Data
public class Donation implements Serializable {
    private Integer donateId;

    private Integer helpId;

    private Integer userId;

    private Integer collectorPersonId;

    private Integer evaluationId;

    private Integer addressId;

    private String scheduledTime;

    private String finishedTime;

    private String donateDetail;

    private static final long serialVersionUID = 1L;

}
