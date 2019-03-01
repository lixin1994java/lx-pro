package com.lxpro.service.serviceInterface;


import com.lxpro.entity.user.User;

/**
 * 用户模块接口
 */
public interface MyUserService {

    /**
     * 用户登录
     * @param user
     * @return
     */
    public User userLogin(User user );

    /**
     * 用户注册
     * @param user
     * @return
     */
    public Integer userRegist(User user);

}


