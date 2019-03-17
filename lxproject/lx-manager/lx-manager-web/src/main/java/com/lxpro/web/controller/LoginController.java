package com.lxpro.web.controller;


import com.lxpro.entity.RestResponseVo;
import com.lxpro.entity.user.User;
import com.lxpro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserService userService;
    @RequestMapping("/login")
    @ResponseBody
    private RestResponseVo userLogin(User user){
        RestResponseVo restResponseVo = new RestResponseVo();
        User uservo = userService.userLogin(user);
        if(uservo!=null){
            if (uservo.getUserStatus()==1){
                restResponseVo.setData(uservo);
                restResponseVo.setSuccess(true);
                restResponseVo.setMeassage("登录成功");
            }else{
                restResponseVo.setSuccess(false);
                restResponseVo.setError_code(1001);
                restResponseVo.setMeassage("账号被禁用");
            }
        }else{
            restResponseVo.setSuccess(false);
            restResponseVo.setError_code(1001);
            restResponseVo.setMeassage("用户名或者密码错误");
        }

        return restResponseVo;

    }

    @RequestMapping("/regist")
    @ResponseBody
    private RestResponseVo regist(User user){

        RestResponseVo restResponseVo = new RestResponseVo();
        Integer integer = userService.userRegist(user);
        if(integer>0){
            restResponseVo.setSuccess(true);
            restResponseVo.setMeassage("注册成功");
            HashMap<String, Object> map = new HashMap<>();
            map.put("userId",integer);
            restResponseVo.setData(map);
            System.out.println(map);
            System.out.println(map);
        }else{
            restResponseVo.setSuccess(false);
            restResponseVo.setError_code(1001);
            restResponseVo.setMeassage("注册失败");
        }

        return restResponseVo;

    }
}
