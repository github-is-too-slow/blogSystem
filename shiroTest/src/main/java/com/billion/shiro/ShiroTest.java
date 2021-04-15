package com.billion.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * @author Billion
 * @create 2021/04/02 11:45
 */
public class ShiroTest {
    public static void main(String[] args) {
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token =
                new UsernamePasswordToken("zhangsan", "zs");
        try {
            subject.login(token);
            System.out.println("登录成功");
            boolean isRole1 = subject.hasRole("role1");
            if(isRole1){
                System.out.println("属于角色role1");
            }else {
                System.out.println("不属于角色role1");
            }
            subject.checkPermission("permission1");
            System.out.println("具有权限permission");
            boolean isSelect = subject.isPermitted("select");
            if(isSelect){
                System.out.println("具有权限select");
            }else {
                System.out.println("不具有权限select");
            }
            //带盐加密, 迭代加密5次
            Md5Hash md5Hash = new Md5Hash("zs", "id", 5);
            System.out.println(md5Hash);
            System.out.println(md5Hash.toString());
            System.out.println(md5Hash.toHex());
            System.out.println(md5Hash.toBase64());
        } catch (UnknownAccountException e) {
            System.out.println("用户名错误");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        } catch (UnauthorizedException e){
            System.out.println("不具有权限permission");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

    }
}
