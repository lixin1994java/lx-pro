package com.lxpro.web.controller;

import com.lxpro.commons.JwtUtils;
import com.lxpro.entity.User;
import com.lxpro.entity.RestResponseVo;
import com.lxpro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserService userService;
    private ObjectError e;
    @RequestMapping("/login")
    @ResponseBody
    private RestResponseVo userLogin(User user){
        String token = null;
        RestResponseVo restResponseVo = new RestResponseVo();
        User uservo = userService.userLogin(user);
        if(uservo!=null){
            if (uservo.getUserStatus()==1){
                HashMap<String, Object> map = new HashMap<>();
                map.put("userId",uservo.getUserId());
                try {
                    //产生登陆token
                    token  = JwtUtils.CreateToken(map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                HashMap<String, Object> data = new HashMap<>();
                data.put("token",token);
                //返回token
                restResponseVo.setData(data);
                restResponseVo.setSuccess(true);
                restResponseVo.setMessage("登录成功");
            }else{
                restResponseVo.setSuccess(false);
                restResponseVo.setError_code(1001);
                restResponseVo.setMessage("账号被禁用");
            }
        }else{
            restResponseVo.setSuccess(false);
            restResponseVo.setError_code(1001);
            restResponseVo.setMessage("用户名或者密码错误");
        }

        return restResponseVo;

    }
    @RequestMapping("/regist")
    @ResponseBody
    private RestResponseVo regist(@Validated({User.aa.class}) User user, BindingResult bs){
        RestResponseVo restResponseVo = new RestResponseVo();
        //后台校验
        if(bs.hasErrors()){
            restResponseVo.setSuccess(false);
            restResponseVo.setError_code(1001);
            List<ObjectError> allErrors = bs.getAllErrors();
            HashMap<String, Object> ValidatedMap = new HashMap<>();
            allErrors.forEach(e->{
                //强转后可以获得字段key
                FieldError e2 = (FieldError) e ;
                ValidatedMap.put(e2.getField(),e.getDefaultMessage());
                    }
            );
            restResponseVo.setData(ValidatedMap);
            restResponseVo.setMessage("注册失败");
            return restResponseVo;
        }
        Integer integer = userService.userRegist(user);
        if(integer>0){
            restResponseVo.setSuccess(true);
            restResponseVo.setMessage("注册成功");
            HashMap<String, Object> map = new HashMap<>();
            map.put("userId",integer);
        }else{
            restResponseVo.setSuccess(false);
            restResponseVo.setError_code(1001);
            restResponseVo.setMessage("注册失败");
        }

        return restResponseVo;
    }
    @RequestMapping("/uplod")
    public void uploadFile  (MultipartFile file, HttpServletRequest request) throws IOException {
        FileOutputStream fileOutputStream =null;
        InputStream inputStream =null;
        try {
            long l1 = System.currentTimeMillis();
            inputStream = file.getInputStream();
            fileOutputStream = new FileOutputStream("C:\\Users\\uptcw\\my"+file.getOriginalFilename());
            byte [] buffer = new byte[1024*1024];
            int lenth=0;
            while ((lenth=inputStream.read(buffer))>0){
                fileOutputStream.write(buffer,0,lenth);
            }
            long l2 = System.currentTimeMillis();
            file.transferTo(new File("C:\\Users\\uptcw\\spring"+file.getOriginalFilename()));
            long l3 = System.currentTimeMillis();
            System.out.println("mytime");
            long  my  =l2- l1;
            long spring =l3-l2;
            for (int i=0;i<my/1000;i++){
                System.out.print("☆");
            }
            System.out.println("");
            System.out.println("springtime");
            for (int i=0;i<spring/1000;i++){
                System.out.print("☆");
            }
        }
        finally {
            inputStream.close();
            fileOutputStream.close();
        }
    }
}
