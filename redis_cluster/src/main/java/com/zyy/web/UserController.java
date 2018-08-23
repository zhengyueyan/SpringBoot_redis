package com.zyy.web;

import com.zyy.common.ResponseMessage;
import com.zyy.entity.User;
import com.zyy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;

/**
 * @Description:
 * @Author: 郑玥延
 * @Date: 13:53 2018/6/19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/all",method = RequestMethod.POST)
    public ResponseMessage getUser(String userName){
        ResponseMessage responseMessage = new ResponseMessage();
        User user = userService.getUserByName(userName);
        responseMessage.setCode(HttpURLConnection.HTTP_OK);
        responseMessage.setResult(user);
        return responseMessage;
    }

}
