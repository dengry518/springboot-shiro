package com.dengry.springbootshiro.dao;

import com.dengry.springbootshiro.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeDao extends JpaRepository<Node, Integer> {
}
