package com.enercomn.web.A00_common.service;

import com.enercomn.web.A00_common.mapper.A00WeixinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhaojy on 2018-02-02.
 */
@Service
public class A00WeixinService {

    @Autowired
    private A00WeixinMapper a00WeixinMapper;



    public int insertUser(String cOpenId,String cNickname,String cImage){

        return a00WeixinMapper.insertUser(cOpenId,cNickname,cImage);
    }

}
