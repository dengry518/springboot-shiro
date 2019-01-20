package com.dengry.springbootshiro.utils;

import com.dengry.springbootshiro.valueObject.Node;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomUtil {
    public static List<Node> nodePo2Vo(List<com.dengry.springbootshiro.entity.Node> nodes) {
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
}
