package com.dengry.springbootshiro.controller;

import com.dengry.springbootshiro.service.MyService;
import com.dengry.springbootshiro.valueObject.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
}
