package com.billion.service.impl;

import com.billion.entity.User;
import com.billion.entity.UserRole;
import com.billion.entity.UserRoleExample;
import com.billion.mapper.UserRoleMapper;
import com.billion.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Billion
 * @create 2021/04/03 20:20
 */
public class UserRoleServiceImpl implements UserRoleService{
    @Autowired
    UserRoleMapper userRoleMapper;
    @Override
    public void setRoles(User user, long[] roleIds) {
        //删除当前用户所有的角色
        UserRoleExample example= new UserRoleExample();
        example.createCriteria().andUidEqualTo(user.getId());
        List<UserRole> urs= userRoleMapper.selectByExample(example);
        for (UserRole userRole : urs)
            userRoleMapper.deleteByPrimaryKey(userRole.getId());

        //设置新的角色关系
        if(null!=roleIds)
            for (long rid : roleIds) {
                UserRole userRole = new UserRole();
                userRole.setRid(rid);
                userRole.setUid(user.getId());
                userRoleMapper.insert(userRole);
            }
    }
    @Override
    public void deleteByUser(long userId) {
        UserRoleExample example= new UserRoleExample();
        example.createCriteria().andUidEqualTo(userId);
        List<UserRole> urs= userRoleMapper.selectByExample(example);
        for (UserRole userRole : urs) {
            userRoleMapper.deleteByPrimaryKey(userRole.getId());
        }
    }
    @Override
    public void deleteByRole(long roleId) {
        UserRoleExample example= new UserRoleExample();
        example.createCriteria().andRidEqualTo(roleId);
        List<UserRole> urs= userRoleMapper.selectByExample(example);
        for (UserRole userRole : urs) {
            userRoleMapper.deleteByPrimaryKey(userRole.getId());
        }
    }
}
