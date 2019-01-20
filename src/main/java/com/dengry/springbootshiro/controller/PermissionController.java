package com.dengry.springbootshiro.controller;

import com.dengry.springbootshiro.entity.Node;
import com.dengry.springbootshiro.entity.Role;
import com.dengry.springbootshiro.entity.User;
import com.dengry.springbootshiro.service.MyService;
import com.dengry.springbootshiro.valueObject.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    MyService myService;

    /**
     * 给角色赋node
     */
    @RequestMapping("/toRoleNodes")
    public String toList() {
        return "permission/roleNode";
    }

    @RequestMapping("/multiSelectTree")
    public String MultiSelectTreeWindow() {
        return "permission/multiSelectTree";
    }

    @RequestMapping("/grantNodes")
    @ResponseBody
    public Json grantNodes(@RequestParam("roleId") Integer roleId, @RequestParam("nodeIds") Integer[] nodeIds) {
        Role role = myService.findRoleById(roleId);
        Set<Node> nodes = new HashSet<>();
        for (Integer nodeId : nodeIds) {
            Node node = new Node();
            node.setId(nodeId);
            nodes.add(node);
        }
        role.setNodes(nodes);
        myService.addRole(role);
        return Json.succ();
    }

    @RequestMapping("/toUserRole")
    public String toUserRole() {
        return "permission/userRole";
    }

    @RequestMapping("/toSelectRole")
    public String toSelectRole() {
        return "permission/selectRole";
    }


    @RequestMapping("/grantRoles")
    @ResponseBody
    public Json grantRoles(@RequestParam("userId") Integer userId, @RequestParam("roleId") Integer roleId) {
        User user = myService.findUserById(userId);
        user.setRole(new Role(roleId));
        myService.addUser(user);
        return Json.succ();
    }
}
