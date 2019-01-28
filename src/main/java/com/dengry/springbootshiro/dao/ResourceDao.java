package com.dengry.springbootshiro.dao;

import com.dengry.springbootshiro.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceDao extends JpaRepository<Resource, Integer>, JpaSpecificationExecutor<Resource> {

    @Query(value = "select resource_id from role_resource where role_id=?1", nativeQuery = true)
    List<Integer> findResourceIdByRoleId(Integer roleId);

    @Query(value = "select resource_id from node_resource where node_id=?1", nativeQuery = true)
    List<Integer> findResIdsByNodeId(Integer nodeId);
}
