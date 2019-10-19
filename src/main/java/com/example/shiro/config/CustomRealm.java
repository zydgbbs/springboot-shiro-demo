package com.example.shiro.config;

import com.example.shiro.domain.User;
import com.example.shiro.utils.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    /**
     * 方法：懒加载，使用的时候才会去执行
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        System.out.println("授权用户["+user.getUserName()+"]角色和权限");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        /*添加角色权限*/
        info.addRole("role:user:list");
        info.addRole("role:user:show");
        info.addRole("role:user:delete");
        /*添加权限*/
        info.addStringPermission("permission:user:list");
        info.addStringPermission("permission:user:show");
        info.addStringPermission("permission:user:delete");
        return info;
    }

    /**
     * 这里可以注入userService,为了方便演示，我就写死了帐号了密码
     * private UserService userService;
     * <p>
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User dbUser = new User();
        dbUser.setUserName("admin");
        dbUser.setPassword(ShiroUtil.SysMd5("admin", "123456"));
        // 从数据库获取对应用户名密码的用户
        // 当验证都通过后，把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                dbUser, //用户
                dbUser.getPassword(), //密码
                ByteSource.Util.bytes(username),
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
