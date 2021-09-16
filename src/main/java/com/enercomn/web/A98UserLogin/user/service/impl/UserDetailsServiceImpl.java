package com.enercomn.web.A98UserLogin.user.service.impl;

import com.enercomn.web.A02EquipmentInfomation.bean.A02EquipmentInfomationBean;
import com.enercomn.web.A02EquipmentInfomation.mapper.A02EquipmentInfomationMapperTK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private A02EquipmentInfomationMapperTK a02EquipmentInfomationMapperTK;

    @Override
    public UserDetails loadUserByUsername(String jobNo) throws UsernameNotFoundException {

        A02EquipmentInfomationBean queryUser = new A02EquipmentInfomationBean();
        queryUser.setJobNo(jobNo);
        queryUser.setCreateDate(null);
        queryUser.setUpdateDate(null);
        A02EquipmentInfomationBean resultUser = a02EquipmentInfomationMapperTK.selectOne(queryUser);
        if(resultUser == null){
            throw new RuntimeException("当前用户不存在！");
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(resultUser.getJobNo()).password(resultUser.getUserPwd()).authorities(resultUser.getRole()).build();
        return userDetails;
    }
}