package com.dengry.springbootshiro.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ShiroController {
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }


//    @RequiresRoles({"admin"})
    @RequestMapping("/toAdmin")
    public String toAdmin() {
        return "admin";
    }
//    @RequiresRoles(value = {"user","admin"},logical = Logical.OR)
    @RequestMapping("/toUser")
    public String toUser() {
        return "user";
    }

    @RequestMapping("/login")
    public String login(String username, String password) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            // rememberme
//            token.setRememberMe(true);
            try {
                // 执行登录.
                currentUser.login(token);
            }
            // 所有认证时异常的父类.
            catch (AuthenticationException e) {
                log.debug("登录失败:{}", e.getMessage());
            }
        }
        return "redirect:/toList";
    }

    @RequestMapping("/toList")
    public String toList() {
        return "list";
    }

    @RequestMapping("/toUnauthorized")
    public String toUnauthorized() {
        return "unauthorized";
    }


}
