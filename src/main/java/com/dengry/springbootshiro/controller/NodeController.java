package com.dengry.springbootshiro.controller;

import com.dengry.springbootshiro.entity.Resource;
import com.dengry.springbootshiro.entity.Role;
import com.dengry.springbootshiro.entity.User;
import com.dengry.springbootshiro.service.MyService;
import com.dengry.springbootshiro.utils.CustomUtil;
import com.dengry.springbootshiro.valueObject.Json;
import com.dengry.springbootshiro.valueObject.Node;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/node")
public class NodeController {
    @Autowired
    MyService myService;

    /**
     * 所有树节点
     *
     * @return
     */
    @RequestMapping("/findLeftTree")
    @ResponseBody
    public List<Node> findLeftTree() {
        List<com.dengry.springbootshiro.entity.Node> nodeList = myService.findLeftTree();
        return CustomUtil.nodePo2Vo(nodeList);
    }

    /**
     * 不同角色不同节点
     *
     * @return
     */
    @RequestMapping("/findLeftTree2")
    @ResponseBody
    public List<Node> findLeftTree2() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Role role = user.getRole();
        List<com.dengry.springbootshiro.entity.Node> nodeList = role.getNodes();
        return CustomUtil.nodePo2Vo(nodeList);
    }

    @RequestMapping("/getResIdes")
    @ResponseBody
    public String getResIdes(Integer nodeId) {
        List<Integer> ids = myService.findResIdsByNodeId(nodeId);
        List<String> newList = new ArrayList<>(ids.size());
        for (Integer myInt : ids) {
            newList.add(String.valueOf(myInt));
        }
        return StringUtils.join(newList, ',');
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

    @RequestMapping("/grantReses")
    @ResponseBody
    public Json grantReses(@RequestParam("nodeId") Integer nodeId, @RequestParam("resIds") Integer[] resIds) {
        com.dengry.springbootshiro.entity.Node node = myService.findNodeById(nodeId);
        List<Resource> resources = new ArrayList<>();
        for (Integer resId : resIds) {
            Resource resource = new Resource();
            resource.setId(resId);
            resources.add(resource);
        }
        node.setResources(resources);

        myService.saveNode(node);
        return Json.succ();
    }
}
