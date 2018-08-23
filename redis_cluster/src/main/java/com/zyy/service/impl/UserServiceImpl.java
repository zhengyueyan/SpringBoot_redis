package com.zyy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyy.config.RedisService;
import com.zyy.entity.User;
import com.zyy.repo.UserRepo;
import com.zyy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RedisService template;

    @Override
    public User getUserByName(String userName){

        User user = null;
        try {
            if(template.getCacheString(userName)!=null){
                String json = template.getCacheString(userName);
                user = JSON.parseObject(json, User.class);
            }else{
                user = userRepo.getUserByName(userName);
                String json = JSONObject.toJSONString(user);
                template.cacheString(userName,json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
