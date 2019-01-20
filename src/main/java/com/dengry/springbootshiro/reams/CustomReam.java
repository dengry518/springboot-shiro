package com.dengry.springbootshiro.reams;

import com.dengry.springbootshiro.entity.Role;
import com.dengry.springbootshiro.entity.User;
import com.dengry.springbootshiro.service.MyService;
import com.dengry.springbootshiro.valueObject.Node;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class CustomReam extends AuthorizingRealm {
    @Autowired
    MyService myService;

    /**
     * 登录认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        User user = myService.findUserByUsername(username);
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        /**
         * principal: 认证的实体信息. 可以是 username, 也可以是从数据表查询得到的用户的实体类对象.
         * credentials:密码(数据库中查出来的密码)
         * realmName:当前 realm 对象的 name. 调用父类的 getName() 方法即可
         * SimpleAuthenticationInfo(Object principal, Object credentials, String realmName)
         *
         * ByteSource credentialsSalt:盐值 可以用一个唯一的字符串，这里用username做盐值(因为它唯一)
         * SimpleAuthenticationInfo(Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName)
         */
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;//new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        ByteSource credentialsSalt = ByteSource.Util.bytes(username); //盐值
        simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, getName());
        return simpleAuthenticationInfo;
    }


    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        User user = (User) principals.getPrimaryPrincipal();
        //2. 利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
        Role role = user.getRole();
        log.debug("role名:{}", role.getShortName());
        Set<String> roles = new HashSet<>();
        roles.add(role.getShortName());

        //2.1根据角色去role_node表中查询node
        List<com.dengry.springbootshiro.entity.Node> nodes = myService.findLeftTree(role.getId());
        role.setNodes(nodes);
        //3. 创建 SimpleAuthorizationInfo, 并设置其 roles 属性.
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        //4. 返回 SimpleAuthorizationInfo 对象.
        return simpleAuthorizationInfo;
    }
}
