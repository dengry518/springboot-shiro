package com.dengry.springbootshiro.controller;

import com.dengry.springbootshiro.utils.Codes;
import com.dengry.springbootshiro.valueObject.Json;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 处理账号异常或者密码错误异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({UnknownAccountException.class, IncorrectCredentialsException.class})
    @ResponseBody
    public Json handleUnknownAccountOrIncorrectCredentialsException(ShiroException e) {
        String eName = e.getClass().getSimpleName();
        return Json.fail(eName, "用户帐号或密码不正确");
    }

    /**
     * Shiro其他异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public Json handleShiroException(ShiroException e) {
        String eName = e.getClass().getSimpleName();
        log.error("shiro执行出错：{}", eName);
        return new Json(eName, false, Codes.SHIRO_ERR, "鉴权或授权过程出错", null);
    }

    /**
     * 用户未登录
     *
     * @return
     */
    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public Json page401() {
        return new Json("401", false, Codes.UNAUTHEN, "用户未登录", null);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Json page403() {
        return new Json("403", false, Codes.SHIRO_ERR, "用户没有访问权限", null);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public Json handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        String eName = e.getClass().getSimpleName();
        return new Json(eName, false, Codes.SERVER_ERR, "数据完整性异常", null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Json customException(Exception e) {
        String eName = e.getClass().getSimpleName();
        return Json.fail(eName, e.getMessage());
    }
}
