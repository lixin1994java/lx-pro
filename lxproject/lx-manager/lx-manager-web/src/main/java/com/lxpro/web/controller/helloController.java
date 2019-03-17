package com.lxpro.web.controller;

import com.lxpro.entity.User;
import com.lxpro.service.UserService;
import com.lxpro.web.rabitMq.MqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class helloController {

    @Value("${rabbitmq.host}")
    public String host;
    @Autowired
    UserService userService;
    @RequestMapping(value = "/lx")
    @ResponseBody
    private User hello (){
        MqSender mqSender = new MqSender();
        mqSender.sendTest();

        /*return userService.userLogin(1l);*/
        return null;
    }
}
