package com.dengry.springbootshiro.controller;

import com.dengry.springbootshiro.entity.User;
import com.dengry.springbootshiro.valueObject.Json;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "user/login";
    }

    @RequestMapping("/toMain")
    public String toMain() {
        return "user/main";
    }

    @PostMapping("/login")
    @ResponseBody
    public Json login(@RequestBody User user) {
        String oper = "user login";
        log.info("{}, body: {}", oper, user);
        //后台校验
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isEmpty(username)) {
            return Json.fail(oper, "用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            return Json.fail(oper, "密码不能为空");
        }

        // 执行登录认证.
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            currentUser.login(token);
        }
        return Json.succ(oper);
    }
}
