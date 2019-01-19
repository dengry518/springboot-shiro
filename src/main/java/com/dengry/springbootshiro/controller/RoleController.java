package com.dengry.springbootshiro.controller;

import com.dengry.springbootshiro.entity.Role;
import com.dengry.springbootshiro.service.MyService;
import com.dengry.springbootshiro.valueObject.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    MyService myService;

    @RequestMapping("/toList")
    public String toList() {
        return "/role/list";
    }

    @RequestMapping("/findRoles")
    @ResponseBody
    public Map findRoles(Integer pageIndex, Integer pageSize, String name) {
        return myService.findRoles(name, pageIndex, pageSize);
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "/role/add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Json add(Role role) {
        log.debug("role is {}", role);
        myService.addRole(role);
        return Json.succ();
    }

    @RequestMapping("/delByIds")
    @ResponseBody
    public Json delByIds(@RequestParam("ids[]")Integer[] ids) {
        myService.delRolesByIds(ids);
        return Json.succ();
    }
}
