package com.zyy.repo;

import com.zyy.entity.User;

/**
 * @Description:
 * @Author: 郑玥延
 * @Date: 13:45 2018/6/19
 */
public interface UserRepo {

    public User getUserByName(String userName);

}
