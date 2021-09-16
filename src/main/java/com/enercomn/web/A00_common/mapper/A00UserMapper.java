package com.enercomn.web.A00_common.mapper;

import com.enercomn.web.A00_common.bean.A00UserBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhaojy on 2018-02-02.
 */
@Component
public interface A00UserMapper {

    A00UserBean getUser(@Param("loginName") String loginName);

    List<String> getPermissions(@Param("roleId") String roleId);

    List<String> getPermissionsByUserId(@Param("userId") String userId);

}
