package com.dengry.springbootshiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.dengry.springbootshiro.entity.Resource;
import com.dengry.springbootshiro.reams.CustomReam;
import com.dengry.springbootshiro.service.MyService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Autowired
    MyService myService;

    @Bean
    public Realm realm() {
        CustomReam customReam = new CustomReam();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        customReam.setCredentialsMatcher(hashedCredentialsMatcher);
        return customReam;
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
        filterChainDefinitionMap.put("/user/logout", "logout");

        List<Resource> resources = myService.findResources();
        for (Resource resource : resources) {
            String url = resource.getUrl();
            String permission="perms["+resource.getPermission()+"]";
            filterChainDefinitionMap.put(url, permission);
        }

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
