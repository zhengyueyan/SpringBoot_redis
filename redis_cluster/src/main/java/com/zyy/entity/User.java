package com.zyy.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: 郑玥延
 * @Date: 13:42 2018/6/19
 */
@Data
public class User implements Serializable{

    private String uuid;

    private String userName;

    private String password;

}
