package com.lxpro.web.controller;

import com.lxpro.entity.user.User;
import com.lxpro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class helloController {

    @Autowired
    UserService userService;
    @RequestMapping(value = "/lx")
    @ResponseBody
    private User hello (){
        /*return userService.userLogin(1l);*/
        return null;
    }
}
