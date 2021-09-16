package com.enercomn.web.A00_common.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by zhaojy on 2018-02-02.
 */
@Component
public interface A00WeixinMapper {

    int insertUser(@Param("cOpenId") String cOpenId,
                   @Param("cNickname") String cNickname,
                   @Param("cImage") String cImage);



}
