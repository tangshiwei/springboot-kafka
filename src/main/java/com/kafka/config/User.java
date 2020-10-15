package com.kafka.config;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Long id;

    private String name;

    private Integer age;

    /**
     * transient 关键字修饰的字段不会被序列化
     */
    private transient String desc;


}

