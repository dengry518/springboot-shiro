package com.dengry.springbootshiro.controller;

import com.dengry.springbootshiro.entity.User;
import com.dengry.springbootshiro.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
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
    public Result login(@RequestBody User user) {
        Result result = new Result();
        result.setSuccess(true);
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            try {
                // 执行登录.
                currentUser.login(token);
            } catch (UnknownAccountException e) {
                log.debug("登录失败:{}", e.getMessage());
                result.setSuccess(false);
                result.setId(1);
                result.setMsg("用户不存在");
            } catch (IncorrectCredentialsException e) {
                log.debug("登录失败:{}", e.getMessage());
                result.setSuccess(false);
                result.setId(2);
                result.setMsg("密码不正确");
            } catch (AuthenticationException e) {
                log.debug("登录失败:{}", e.getMessage());
                result.setSuccess(false);
                result.setId(3);
                result.setMsg("其他异常");
            }
        }
        return result;
    }
}
