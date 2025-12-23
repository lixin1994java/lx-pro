package com.lxpro.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class helloController {

    @RequestMapping(value = "/lx")
    public String hello (){
        return "ok";
    }
}
