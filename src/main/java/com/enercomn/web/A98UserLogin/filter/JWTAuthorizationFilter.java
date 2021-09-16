package com.enercomn.web.A98UserLogin.filter;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A98UserLogin.jwt.JwtTokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * 验证成功当然就是进行鉴权了
 * 登录成功之后走此类进行鉴权操作
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String requestURL = request.getRequestURL().toString();
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        boolean bol1 = tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX);
        if (requestURL.indexOf(".png") != -1||requestURL.indexOf(".jpeg") != -1||requestURL.indexOf(".gif") != -1||
                requestURL.indexOf("/auth/login") != -1||
                requestURL.indexOf("/export/") != -1||
                requestURL.indexOf("/upload/") != -1||
                requestURL.indexOf("doc.html") != -1||
                requestURL.indexOf("/webjars") != -1) {
            chain.doFilter(request, response);
            return;
        }else if (bol1) {
            this.result(response, new ResultMsg(ResultStatusCode.USER_TOKEN_ERROR.getResultCode(), ResultStatusCode.USER_TOKEN_ERROR.getResultMessage()));
            return;
        }
        try {
            // 如果请求头中有token，则进行解析，并且设置认证信息
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
            super.doFilterInternal(request, response, chain);
        } catch (JwtException e) {
            this.result(response, new ResultMsg(ResultStatusCode.REQUEST_FAIL.getResultCode(), "token错误"));
        }
    }

    // 这里从token中获取用户信息并新建一个token
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        boolean b = JwtTokenUtils.isExpiration(token);
        if (b) {
            throw new RuntimeException("token超时了");
        }
        String username = JwtTokenUtils.getName(token);
        String role = JwtTokenUtils.getUserRole(token);
        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null,
                    Collections.singleton(new SimpleGrantedAuthority(role))
            );
        }
        return null;
    }


    protected void result(HttpServletResponse response, ResultMsg resultMsg) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");


        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(resultMsg));
    }

}