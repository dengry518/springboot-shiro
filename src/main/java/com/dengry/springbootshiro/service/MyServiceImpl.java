package com.dengry.springbootshiro.service;

import com.dengry.springbootshiro.dao.NodeDao;
import com.dengry.springbootshiro.dao.RoleDao;
import com.dengry.springbootshiro.dao.UserDao;
import com.dengry.springbootshiro.entity.Role;
import com.dengry.springbootshiro.entity.User;
import com.dengry.springbootshiro.valueObject.Node;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class MyServiceImpl implements MyService {
    @Autowired
    UserDao userDao;
    @Autowired
    NodeDao nodeDao;
    @Autowired
    RoleDao roleDao;

    @Override
    public void delNode(Integer id) {
        nodeDao.deleteById(id);
    }

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

    @Override
    public void addNode(Node node) {
        com.dengry.springbootshiro.entity.Node n = new com.dengry.springbootshiro.entity.Node();
        BeanUtils.copyProperties(node, n);
        Integer pId = node.getPid();
        if (pId != null) {
            n.setParent(new com.dengry.springbootshiro.entity.Node(pId));
        }
        nodeDao.save(n);
    }

    @Override
    public Map<String, Object> findRoles(String name, Integer pageIndex, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<Role> page = roleDao.findAll(new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!name.equals("")) {
                    predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageRequest);

        Map<String, Object> map = new HashMap();
        List<Role> brands = page.getContent();
        long totalElements = page.getTotalElements();
        map.put("total", totalElements);
        map.put("data", brands);
        return map;
    }
}
