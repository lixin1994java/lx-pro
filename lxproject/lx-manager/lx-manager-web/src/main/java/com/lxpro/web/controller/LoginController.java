package com.lxpro.web.controller;


import com.lxpro.entity.RestResponseVo;
import com.lxpro.entity.user.User;
import com.lxpro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserService userService;
    @RequestMapping("/login")
    public RestResponseVo userLogin(User user){
        RestResponseVo restResponseVo = new RestResponseVo();
        if (user == null || isBlank(user.getUsername()) || isBlank(user.getPassword())) {
            restResponseVo.setSuccess(false);
            restResponseVo.setError_code(1001);
            restResponseVo.setMeassage("参数错误");
            return restResponseVo;
        }
        User uservo = userService.userLogin(user);
        if(uservo!=null){
            if (uservo.getUserStatus() != null && uservo.getUserStatus()==1){
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
    public RestResponseVo regist(User user){

        RestResponseVo restResponseVo = new RestResponseVo();
        if (user == null || isBlank(user.getUsername()) || isBlank(user.getPassword())) {
            restResponseVo.setSuccess(false);
            restResponseVo.setError_code(1001);
            restResponseVo.setMeassage("参数错误");
            return restResponseVo;
        }
        Integer integer = userService.userRegist(user);
        if(integer>0){
            restResponseVo.setSuccess(true);
            restResponseVo.setMeassage("注册成功");
            HashMap<String, Object> map = new HashMap<>();
            if (user.getUserId() != null) {
                map.put("userId", user.getUserId());
            }
            map.put("affectedRows", integer);
            restResponseVo.setData(map);
        }else{
            restResponseVo.setSuccess(false);
            restResponseVo.setError_code(1001);
            restResponseVo.setMeassage("注册失败");
        }

        return restResponseVo;

    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
