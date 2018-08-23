package com.zyy.mapper;

import com.zyy.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author: 郑玥延
 * @Date: 13:45 2018/6/19
 */
@Mapper
public interface UserMapper {

    public User getUserByUserName(String userName);

}
