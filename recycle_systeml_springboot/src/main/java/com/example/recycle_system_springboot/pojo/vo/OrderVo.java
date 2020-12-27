package com.example.recycle_system_springboot.pojo.vo;

import com.example.recycle_system_springboot.pojo.entity.RecycleOrders;
import lombok.Data;

import java.util.List;

@Data
public class OrderVo  extends RecycleOrders {
    private Integer userId;
    private List<ItemVo> tableData;
}
