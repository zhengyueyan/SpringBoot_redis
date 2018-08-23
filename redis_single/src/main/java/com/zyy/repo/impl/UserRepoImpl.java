package com.zyy.repo.impl;

import com.zyy.entity.User;
import com.zyy.mapper.UserMapper;
import com.zyy.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: 郑玥延
 * @Date: 13:46 2018/6/19
 */
@Repository
public class UserRepoImpl implements UserRepo {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByUserName(userName);
    }
}
