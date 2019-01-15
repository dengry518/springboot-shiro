package com.dengry.springbootshiro.service;

import com.dengry.springbootshiro.dao.NodeDao;
import com.dengry.springbootshiro.dao.UserDao;
import com.dengry.springbootshiro.entity.User;
import com.dengry.springbootshiro.valueObject.Node;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MyServiceImpl implements MyService {
    @Autowired
    UserDao userDao;

    @Autowired
    NodeDao nodeDao;

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public List<Node> findLeftTree() {
        List<com.dengry.springbootshiro.entity.Node> nodes = nodeDao.findAll();
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
