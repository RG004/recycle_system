package com.example.recycle_system_springboot.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * recycle_orders
 * @author
 */
@Data
public class RecycleOrders implements Serializable {
    //订单实体类
    private Integer recycleOrderId;

    private Integer addressId;

    private Integer collectorPersonId;

    private Integer evaluationId;

    private Integer userId;

    private Date scheduledTime;

    private Date finishedTime;

    private Double totalAmount;

    private static final long serialVersionUID = 1L;

}
