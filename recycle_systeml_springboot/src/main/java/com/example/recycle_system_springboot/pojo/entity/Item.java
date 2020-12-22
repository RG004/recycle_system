package com.example.recycle_system_springboot.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * item
 * @author
 */
@Data
public class Item implements Serializable {
    //物品实体类
    private Integer itemId;

    private Integer itemTypeId;

    private String itemName;

    private Double itemPrice;

    private static final long serialVersionUID = 1L;

}
