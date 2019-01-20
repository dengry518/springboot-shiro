package com.dengry.springbootshiro.controller;

import com.dengry.springbootshiro.entity.Node;
import com.dengry.springbootshiro.entity.Role;
import com.dengry.springbootshiro.entity.User;
import com.dengry.springbootshiro.service.MyService;
import com.dengry.springbootshiro.valueObject.Json;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    MyService myService;

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

    @RequestMapping("/toList")
    public String toList() {
        return "user/list";
    }

    @RequestMapping("/findUsers")
    @ResponseBody
    public Map findUsers(String username, Integer pageIndex, Integer pageSize) {
        return myService.findUsers(username, pageIndex, pageSize);
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Json add(User user) {
        log.debug("user is {}", user);

        //MD5加密,新用户原始密码都是123456
        String algorithmName = "MD5";
        Object source = "123456";
        Object salt = ByteSource.Util.bytes(user.getUsername());
        int hashIterations = 1024;
        SimpleHash simpleHash = new SimpleHash(algorithmName, source, salt, hashIterations);
        user.setPassword(simpleHash.toString());

        myService.addUser(user);
        return Json.succ();
    }

    @RequestMapping("/findUserById/{id}")
    @ResponseBody
    public User findUserById(@PathVariable("id") Integer id) {
        return myService.findUserById(id);
    }

    @RequestMapping("/delByIds")
    @ResponseBody
    public Json delByIds(@RequestParam("ids[]") Integer[] ids) {
        myService.delUsersByIds(ids);
        return Json.succ();
    }

}
