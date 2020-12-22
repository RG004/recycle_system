package com.example.recycle_system_springboot.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * collector
 * @author
 */
@Data
public class Collector implements Serializable {
    //派送员实体类
    private Integer collectorPersonId;

    private Integer siteId;

    private String collectorName;

    private String userName;

    private String password;

    private String phone;

    private String idcardNumber;

    private String photo;

    private static final long serialVersionUID = 1L;

}
