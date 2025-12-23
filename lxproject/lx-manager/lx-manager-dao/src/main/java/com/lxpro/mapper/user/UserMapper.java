package com.lxpro.mapper.user;

import com.lxpro.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据访问层。
 *
 * 说明：当前仓库未包含对应的 SQL 映射（XML/注解），因此这里只定义接口以保证编译与依赖关系正确。
 */
@Mapper
public interface UserMapper {

    User userLogin(User user);

    int insert(User user);
}

