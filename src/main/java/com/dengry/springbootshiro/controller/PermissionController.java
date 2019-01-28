package com.dengry.springbootshiro.controller;

import com.dengry.springbootshiro.entity.Node;
import com.dengry.springbootshiro.entity.Resource;
import com.dengry.springbootshiro.entity.Role;
import com.dengry.springbootshiro.entity.User;
import com.dengry.springbootshiro.service.MyService;
import com.dengry.springbootshiro.valueObject.Json;
import com.dengry.springbootshiro.valueObject.Perm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
        List<Node> nodes = new ArrayList<>();
        for (Integer nodeId : nodeIds) {
            Node node = new Node();
            node.setId(nodeId);
            nodes.add(node);
        }
        role.setNodes(nodes);
        myService.addRole(role);
        return Json.succ();
    }

    @RequestMapping("/grantReses")
    @ResponseBody
    public Json grantReses(@RequestParam("roleId") Integer roleId, @RequestParam("resIds") Integer[] resIds) {
        Role role = myService.findRoleById(roleId);
        List<Resource> resources = new ArrayList<>();
        for (Integer resId : resIds) {
            Resource resource = new Resource();
            resource.setId(resId);
            resources.add(resource);
        }
        role.setResources(resources);
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

    @RequestMapping("/toRoleRes")
    public String toRoleRes() {
        return "permission/selectRes";
    }


    @RequestMapping("/findReses")
    @ResponseBody
    public List<Perm> findReses(@RequestParam(value = "resIds", required = false) Integer[] resIds) {
        return myService.findPermissiones(resIds);
    }

    /////////////////////给节点授资源模块begin

    /**
     * 跳转到给节点授资源页面
     */
    @RequestMapping("/toNodeGrantRes")
    public String toNodeGrantRes() {
        return "permission/list";
    }


    /**
     * 查询节点拥有资源
     *
     * @param nodeId
     * @return
     */
    @RequestMapping("/findResByNode")
    @ResponseBody
    public List<Resource> findResByNode(@RequestParam(value = "nodeId", required = false) Integer nodeId) {
        return myService.findResByNode(nodeId);
    }


    @RequestMapping("/toSelectReses")
    public String toSelectReses() {
        return "permission/selectReses";
    }

    /////////////////////给节点授资源模块end
}
