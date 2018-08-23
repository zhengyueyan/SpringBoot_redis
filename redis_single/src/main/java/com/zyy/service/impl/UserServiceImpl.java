package com.zyy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyy.entity.User;
import com.zyy.repo.UserRepo;
import com.zyy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: 郑玥延
 * @Date: 13:46 2018/6/19
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StringRedisTemplate template;

    @Override
    public User getUserByName(String userName) {

        User user;

        if(!template.hasKey(userName)){
            user = userRepo.getUserByName(userName);
            String json = JSONObject.toJSONString(user);
            template.opsForValue().set(userName, json);
            return user;
        }else{
            String json = template.opsForValue().get(userName);
            user = JSON.parseObject(json, User.class);
        }
        return user;
    }
}
