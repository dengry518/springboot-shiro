package com.dengry.springbootshiro;

import com.dengry.springbootshiro.dao.NodeDao;
import com.dengry.springbootshiro.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootShiroApplicationTests {

    @Autowired
    MyService myService;
    @Autowired
    NodeDao nodeDao;

    @Test
    public void contextLoads() {
        // 级别由低到高 trace<debug<info<warn<error
        log.trace("这是一个trace日志...");
        log.debug("这是一个debug日志...");
        // SpringBoot默认是info级别，只会输出info及以上级别的日志
        log.info("这是一个info日志...");
        log.warn("这是一个warn日志...");
        log.error("这是一个error日志...");
    }

    @Test
    public void gm() {
        String algorithmName = "MD5";
        Object source = "123456";
        Object salt = ByteSource.Util.bytes("tom");
        int hashIterations = 1024;
        SimpleHash simpleHash = new SimpleHash(algorithmName, source, salt, hashIterations);
        System.out.println(simpleHash.toString());
    }
    @Test
    public void findNodeIdByRoleId(){
        List<Integer> integers = nodeDao.findNodeIdByRoleId(1);
    }


}

