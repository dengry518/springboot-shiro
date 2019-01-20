package com.dengry.springbootshiro.controller;

import com.dengry.springbootshiro.entity.Role;
import com.dengry.springbootshiro.entity.User;
import com.dengry.springbootshiro.service.MyService;
import com.dengry.springbootshiro.valueObject.Json;
import com.dengry.springbootshiro.valueObject.Node;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/node")
public class NodeController {
    @Autowired
    MyService myService;

    @RequestMapping("/findLeftTree")
    @ResponseBody
    public List<Node> findLeftTree() {
        return myService.findLeftTree();
    }

    @RequestMapping("/findLeftTree2")
    @ResponseBody
    public List<Node> findLeftTree2() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Role role = user.getRole();
        Set<com.dengry.springbootshiro.entity.Node> nodes = role.getNodes();

        List<Node> nodeList = new ArrayList<>();
        for (com.dengry.springbootshiro.entity.Node node : nodes) {
            Node n = new Node();
            BeanUtils.copyProperties(node, n);
            if (node.getParent() != null) {
                n.setPid(node.getParent().getId());
            }
            nodeList.add(n);
        }
        return nodeList;
    }

    @RequestMapping("/toMenu")
    public String toMenu() {
        return "node/menu";
    }

    @RequestMapping("/toAddRoot")
    public String toAddRoot() {
        return "node/addRoot";
    }

    @RequestMapping("/addNode")
    @ResponseBody
    public Json addNode(Node node) {
        log.debug("node is {}", node);
        myService.addNode(node);
        return Json.succ();
    }

    @RequestMapping("/toAddNode")
    public String toAddNode() {
        return "node/addNode";
    }

    @RequestMapping("/delNode")
    @ResponseBody
    public Json delNode(Integer id) {
        myService.delNode(id);
        return Json.succ();
    }

    @RequestMapping("/toUpdateNode")
    public String toUpdateNode() {
        return "node/updateNode";
    }
}
