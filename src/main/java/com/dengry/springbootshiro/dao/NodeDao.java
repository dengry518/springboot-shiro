package com.dengry.springbootshiro.dao;

import com.dengry.springbootshiro.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NodeDao extends JpaRepository<Node, Integer> {
    @Query(value = "select node_id from role_node where role_id= ?1", nativeQuery = true)
    List<Integer> findNodeIdByRoleId(Integer roleId);

    @Query(value = "select resource_id from node_resource where node_id=?1", nativeQuery = true)
    List<Integer> findResIdsByNodeId(Integer nodeId);
}
