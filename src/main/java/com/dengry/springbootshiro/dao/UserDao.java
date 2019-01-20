package com.dengry.springbootshiro.dao;

import com.dengry.springbootshiro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    User findUserByUsername(String username);

    @Modifying
    @Query(value = "update t_user u set u.name= ?1 where u.id= ?2",nativeQuery = true)
    void updateUser(String name, Integer id);
}
