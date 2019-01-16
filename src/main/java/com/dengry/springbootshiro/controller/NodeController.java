package com.dengry.springbootshiro.controller;

import com.dengry.springbootshiro.service.MyService;
import com.dengry.springbootshiro.valueObject.Json;
import com.dengry.springbootshiro.valueObject.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
}
