package com.lxpro.service;


import com.lxpro.entity.user.User;
import com.lxpro.mapper.user.UserMapper;
import com.lxpro.service.serviceInterface.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements MyUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User userLogin(User user ){
        User tUser = userMapper.userLogin(user);
        return  tUser;
    }

    @Override
    public Integer userRegist(User user) {
        int insert = userMapper.insert(user);
        return insert;
    }
}
