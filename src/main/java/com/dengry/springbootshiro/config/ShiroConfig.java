package com.dengry.springbootshiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.dengry.springbootshiro.reams.MyReam;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    @Bean
    public Realm realm() {
        MyReam myReam = new MyReam();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        myReam.setCredentialsMatcher(hashedCredentialsMatcher);
        return myReam;
    }

    /**
     * 开启类(controller,service)的方法中加入@RequiresRole等注解
     *
     * @return
     */
    /*@Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        //controller的方法采用cglib生成aop
        creator.setProxyTargetClass(true);
        return creator;
    }*/


    /**
     * 权限配置 2种方式 a.url b.方法上加@RequiresRole等注解
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition defaultShiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap();
        //静态资源不拦截
        filterChainDefinitionMap.put("/miniui/**", "anon");
        //跳转到登录页不拦截
        filterChainDefinitionMap.put("/user/toLogin", "anon");
        //登录请求不拦截
        filterChainDefinitionMap.put("/user/login", "anon");

        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/toAdmin", "roles[admin]");
        filterChainDefinitionMap.put("/toUser", "roles[user]");
        filterChainDefinitionMap.put("/**", "authc");
        defaultShiroFilterChainDefinition.addPathDefinitions(filterChainDefinitionMap);
        return defaultShiroFilterChainDefinition;
    }

    @Bean
    public CacheManager cacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehCacheManager;
    }

    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }


}
