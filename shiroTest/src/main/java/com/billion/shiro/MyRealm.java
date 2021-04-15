package com.billion.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * @author Billion
 * @create 2021/04/02 13:53
 */
public class MyRealm extends AuthenticatingRealm{
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        System.out.println("开始验证");
        String username = authenticationToken.getPrincipal().toString();
        String password = new String((char[])authenticationToken.getCredentials());
        System.out.println(username + " = " + password);
        //判断明文用户名是否在数据库中存在
        String usernameIndb = "zhangsan";
        if(username.equals(usernameIndb)){
            String passwordIndb = "zs";
            System.out.println("验证结束");
            //进行下一步数据中明文密码验证
            //return new SimpleAuthenticationInfo(usernameIndb, passwordIndb, usernameIndb);
            //进行下一步数据中md5加密密码验证，将用户id作为盐，使用Shiro提供的 HashedCredentialsMatcher进行验证
            String md5passwordIndb = "d60fa99a3b4fe13ad82e54397f73dc72";
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            return new SimpleAuthenticationInfo(usernameIndb, md5passwordIndb,
                    ByteSource.Util.bytes("id" + ""), usernameIndb);
        }
        //用户名存在，在下一步密码验证中直接报UnknownAccountException
        return null;
    }
}
