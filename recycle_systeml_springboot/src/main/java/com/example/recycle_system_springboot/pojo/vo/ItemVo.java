package com.example.recycle_system_springboot.pojo.vo;

import com.example.recycle_system_springboot.pojo.entity.Item;
import lombok.Data;

import java.util.List;

@Data
public class ItemVo  {
    private Integer itemTypeId;
    private String itemTypeName;
    private List<Item> itemsList;

}
