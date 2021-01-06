package com.example.recycle_system_springboot.pojo.vo;

import lombok.Data;

@Data
public class ItemVo {
    private String itemTypeName;
    private String itemName;
    private double itemPrice;
    private int itemId;
    private int itemTypeId;
    private String itemPic;
}
