package com.enercomn.common;

import com.enercomn.utils.ResultMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaojy on 2018-02-02.
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @org.springframework.web.bind.annotation.ExceptionHandler(ShiroException.class)
    public ResultMsg handle401(ShiroException e) {
        return new ResultMsg(401, e.getMessage(), null);
    }

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException.class)
    public ResultMsg handle403() {
        return new ResultMsg(403, "Unauthorized", null);
    }

    // 捕捉其他所有异常
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultMsg globalException(HttpServletRequest request, Throwable ex) {
        ex.printStackTrace();
        log.error("====================其他异常被捕获============================",ex);
        return new ResultMsg(getStatus(request).value(), ex.getMessage(), null);
    }


    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultMsg handleParamsException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        List<String> invalidArguments = new ArrayList<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            log.info("{}", error.getRejectedValue());
            log.info("{}", error.getField());
            invalidArguments.add(error.getDefaultMessage());
        }
        return new ResultMsg(getStatus(request).value(), "表单内容填写有误", invalidArguments);
    }
}
