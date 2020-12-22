package com.example.recycle_system_springboot.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * recycle_order_detail
 * @author
 */
@Data
public class RecycleOrderDetail implements Serializable {
    //订单详情实体类
    private Integer recycleOrderDetailId;

    private Integer itemId;

    private Integer recycleOrderId;

    private Integer quantity;

}
