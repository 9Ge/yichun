package com.enercomn.shiro;

import com.enercomn.jwt.TokenResolver;
import com.enercomn.web.A00_common.bean.A00UserBean;
import com.enercomn.web.A00_common.service.A00UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 具体权限判断类
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Resource
    private A00UserService service;

    @Resource
    private TokenResolver tokenResolver;

    /**
     *必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = tokenResolver.getLoginName(principals.toString());
        A00UserBean user = service.getUser(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(user.getRole());
        Set<String> permission = user.getPermissions();
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        if (token == null) {
            throw new AuthenticationException("token invalid");
        }
        String username = tokenResolver.getLoginName(token);
        if (username == null || "-1".equals(username)) {
            throw new AuthenticationException("token invalid");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
