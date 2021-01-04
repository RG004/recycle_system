package com.example.recycle_system_springboot.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * help_the_poor
 * @author
 */
@Data
public class HelpThePoor implements Serializable {
    private Integer helpId;

    private String helpName;

    private Double longitude;

    private Double latitude;

    private String helpDetail;

    private String helpStatus;

    private static final long serialVersionUID = 1L;
}
