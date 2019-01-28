package com.dengry.springbootshiro.service;

import com.dengry.springbootshiro.dao.NodeDao;
import com.dengry.springbootshiro.dao.ResourceDao;
import com.dengry.springbootshiro.dao.RoleDao;
import com.dengry.springbootshiro.dao.UserDao;
import com.dengry.springbootshiro.entity.Resource;
import com.dengry.springbootshiro.entity.Role;
import com.dengry.springbootshiro.entity.User;
import com.dengry.springbootshiro.valueObject.Node;
import com.dengry.springbootshiro.valueObject.Perm;
import com.dengry.springbootshiro.valueObject.Res;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.*;

@Transactional
@Service
public class MyServiceImpl implements MyService {
    @Autowired
    UserDao userDao;
    @Autowired
    NodeDao nodeDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    ResourceDao resourceDao;

    @Override
    public void delNode(Integer id) {
        nodeDao.deleteById(id);
    }

    @Override
    public Role findRoleById(Integer id) {
        Optional<Role> optional = roleDao.findById(id);
        return optional.get();
    }

    @Override
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    public User findUserById(Integer id) {
        Optional<User> optional = userDao.findById(id);
        return optional.get();
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public List<com.dengry.springbootshiro.entity.Node> findLeftTree() {
        return nodeDao.findAll();
    }


    @Override
    public List<Perm> findPermissiones(Integer[] resIds) {
        List<Perm> permissions = new ArrayList<>();
        List<com.dengry.springbootshiro.entity.Node> nodes = nodeDao.findAll();
        for (com.dengry.springbootshiro.entity.Node node : nodes) {
            Perm perm = new Perm();
            perm.setName(node.getText());
            perm.setId(node.getId());

            if (node.getParent() != null) {
                perm.setPid(node.getParent().getId());
            }

            if (node.getUrl() != null) {
                //根据节点id去node_resource查询资源
                List<Resource> resources = this.findResesByNodeId(node.getId());
                if (resources.size() != 0) {
                    List<Res> resList = new ArrayList<>();
                    for (Resource resource : resources) {
                        Res res = new Res();
                        res.setId(resource.getId());
                        if(isExist(resource,resIds)){
                            res.setChecked(true);
                        }
                        res.setName(resource.getName());
                        res.setAction(resource.getPermission());
                        resList.add(res);
                    }
                    perm.setFunctions(resList);
                }
            }
            permissions.add(perm);
        }
        return permissions;
    }

    public boolean isExist(Resource resource, Integer[] resIds) {
        boolean f = false;
        for (Integer resId : resIds) {
            if (resource.getId() == resId) {
                f = true;
                break;
            }
        }
        return f;
    }

    @Override
    public List<com.dengry.springbootshiro.entity.Node> findLeftTree(Integer roleId) {
        List<Integer> nodeIds = nodeDao.findNodeIdByRoleId(roleId);
        List<com.dengry.springbootshiro.entity.Node> nodes = new ArrayList<>();
        for (Integer nodeId : nodeIds) {
            Optional<com.dengry.springbootshiro.entity.Node> optional = nodeDao.findById(nodeId);
            nodes.add(optional.get());
        }
        return nodes;
    }

    @Override
    public List<Resource> findResesByRoleId(Integer roleId) {
        List<Integer> resIds = resourceDao.findResourceIdByRoleId(roleId);
        List<Resource> resources = new ArrayList<>();
        for (Integer resId : resIds) {
            Optional<Resource> optional = resourceDao.findById(resId);
            resources.add(optional.get());
        }
        return resources;
    }

    @Override
    public List<Resource> findResesByNodeId(Integer nodeId) {
        List<Integer> resIds = resourceDao.findResIdsByNodeId(nodeId);
        List<Resource> resources = new ArrayList<>();
        for (Integer resId : resIds) {
            Optional<Resource> optional = resourceDao.findById(resId);
            resources.add(optional.get());
        }
        return resources;
    }

    @Override
    public List<Integer> findResIdsByNodeId(Integer nodeId) {
        return resourceDao.findResIdsByNodeId(nodeId);
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
        Page<Role> page = roleDao.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(name)) {
                predicates.add(cb.like(root.get("name"), "%" + name + "%"));
            }
            query.where(predicates.toArray(new Predicate[predicates.size()]));
            return null;
        }, pageRequest);

        return page2Map(page);
    }

    @Override
    public Map<String, Object> findUsers(String username, Integer pageIndex, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<User> page = userDao.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(username)) {
                predicates.add(cb.like(root.get("username"), "%" + username + "%"));
            }
            query.where(predicates.toArray(new Predicate[predicates.size()]));
            return null;
        }, pageRequest);
        return page2Map(page);
    }

    @Override
    public Map<String, Object> findReses(String url, Integer pageIndex, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<Resource> page = resourceDao.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(url)) {
                predicates.add(cb.like(root.get("url"), "%" + url + "%"));
            }
            query.where(predicates.toArray(new Predicate[predicates.size()]));
            return null;
        }, pageRequest);
        return page2Map(page);
    }

    @Override
    public List<Resource> findResByNode(Integer nodeId) {
        List<Integer> resIds = nodeDao.findResIdsByNodeId(nodeId);
        List<Resource> resources = new ArrayList<>();
        for (Integer resId : resIds) {
            Optional<Resource> optional = resourceDao.findById(resId);
            resources.add(optional.get());
        }
        return resources;
    }

    @Override
    public com.dengry.springbootshiro.entity.Node findNodeById(Integer id) {
        Optional<com.dengry.springbootshiro.entity.Node> optional = nodeDao.findById(id);
        return optional.get();
    }

    public <T extends Object> Map<String, Object> page2Map(Page<T> page) {
        Map<String, Object> map = new HashMap();
        List<T> content = page.getContent();
        long totalElements = page.getTotalElements();
        map.put("total", totalElements);
        map.put("data", content);
        return map;
    }

    @Override
    public void addRole(Role role) {
        roleDao.save(role);
    }

    @Override
    public void delRolesByIds(Integer[] ids) {
        for (Integer id : ids) {
            roleDao.deleteById(id);
        }
    }

    @Override
    public void delUsersByIds(Integer[] ids) {
        for (Integer id : ids) {
            userDao.deleteById(id);
        }
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user.getName(), user.getId());
    }

    @Override
    public void saveResource(Resource resource) {
        resourceDao.save(resource);
    }

    @Override
    public void saveNode(com.dengry.springbootshiro.entity.Node node) {
        nodeDao.save(node);
    }

    @Override
    public void delResById(Integer id) {
        resourceDao.deleteById(id);
    }

    @Override
    public List<Resource> findResources() {
        return resourceDao.findAll();
    }
}
