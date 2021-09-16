package com.enercomn.web.A00_common.service;

import com.enercomn.web.A00_common.bean.A00UserBean;
import com.enercomn.web.A00_common.mapper.A00UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaojy on 2018-02-02.
 */
@Service
public class A00UserService {

    @Autowired
    private A00UserMapper userMapper;

    @Cacheable(value = "user")
    public A00UserBean getUser(String loginName) {
        A00UserBean user = userMapper.getUser(loginName);
        List<String> permissions = userMapper.getPermissions(user.getRole());
        user.setPermissions(new HashSet<>(permissions));
        return user;
    }


    public Set<String> getUserPermissions(String userId) {
        List<String> permissions = userMapper.getPermissionsByUserId(userId);
        return new HashSet<>(permissions);
    }

}
