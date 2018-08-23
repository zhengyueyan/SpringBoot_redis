package com.zyy.service;

import com.zyy.entity.User;
import org.springframework.cache.annotation.Cacheable;

/**
 * @Description:
 * @Author: 郑玥延
 * @Date: 13:46 2018/6/19
 */
public interface UserService {

    public User getUserByName(String userName);

}
