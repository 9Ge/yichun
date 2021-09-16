package com.enercomn.web.A98UserLogin.filter;

import com.alibaba.fastjson.JSONObject;
import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A98UserLogin.handler.CustomSimpleUrlAuthenticationFailureHandler;
import com.enercomn.web.A98UserLogin.jwt.JwtTokenUtils;
import com.enercomn.web.A98UserLogin.user.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.security.auth.login.LoginException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法 ,
 * attemptAuthentication：接收并解析用户凭证。 
 * successfulAuthentication：用户成功登录后，这个方法会被调用，我们在这个方法里生成token并返回。
 */
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  
  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
    this.setMessageSource(messageSource());
    super.setFilterProcessesUrl("/auth/login");
  }

  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource
            = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:messages.properties");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }



  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response) throws AuthenticationException {
    User loginUser = null;
    // 从输入流中获取到登录的信息
    try {
      loginUser = new ObjectMapper().readValue(request.getInputStream(), User.class);
    } catch (IOException e) {
      return null;
    }
    try {
      return authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginUser.getName(), loginUser.getPwd())
      );
    } catch (AuthenticationException e) {
      log.error(e.getMessage());
      throw e;
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request,
                                          HttpServletResponse response,
                                          FilterChain chain,
                                          Authentication authResult) throws IOException, ServletException {
    org.springframework.security.core.userdetails.User jwtUser= (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
    Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
    String role = "";
    for (GrantedAuthority authority : authorities){
      role = authority.getAuthority();
    }

    String token = JwtTokenUtils.createToken(jwtUser.getUsername(), role);
    successfulAuthentication(response,JwtTokenUtils.TOKEN_PREFIX + token);
  }


  protected void successfulAuthentication(HttpServletResponse response,String tokenStr) throws IOException, ServletException {
    ResultMsg resultMsg = new ResultMsg();
    resultMsg.setResultCode(200);
    resultMsg.setResultMessage("登陆成功");
    resultMsg.setResultObject(tokenStr);
    logResult(response,resultMsg);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    ResultMsg resultMsg = new ResultMsg();
    resultMsg.setResultCode(403);
    resultMsg.setResultMessage("登陆失败,"+failed.getMessage());
    resultMsg.setResultObject(failed.getMessage());
    logResult(response,resultMsg);
  }


  public void logResult( HttpServletResponse response, ResultMsg resultMsg) throws IOException {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json; charset=utf-8");
    ObjectMapper objectMapper  = new ObjectMapper();
    response.getWriter().write(objectMapper.writeValueAsString(resultMsg));
  }
}