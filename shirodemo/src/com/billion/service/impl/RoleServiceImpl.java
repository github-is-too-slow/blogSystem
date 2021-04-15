package com.billion.service.impl;

import com.billion.entity.*;
import com.billion.mapper.RoleMapper;
import com.billion.mapper.UserRoleMapper;
import com.billion.service.RoleService;
import com.billion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Billion
 * @create 2021/04/03 20:14
 */
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    UserService userService;

    @Override
    public Set<String> listRoleNames(String userName) {
        Set<String> result = new HashSet<>();
        List<Role> roles = listRoles(userName);
        for (Role role : roles) {
            result.add(role.getName());
        }
        return result;
    }

    @Override
    public List<Role> listRoles(String userName) {
        List<Role> roles = new ArrayList<>();
        User user = userService.getByName(userName);
        if(null==user)
            return roles;

        roles = listRoles(user);
        return roles;
    }

    @Override
    public void add(Role u) {
        roleMapper.insert(u);
    }

    @Override
    public void delete(Long id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Role u) {
        roleMapper.updateByPrimaryKeySelective(u);
    }

    @Override
    public Role get(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> list(){
        RoleExample example =new RoleExample();
        example.setOrderByClause("id desc");
        return roleMapper.selectByExample(example);

    }

    @Override
    public List<Role> listRoles(User user) {
        List<Role> roles = new ArrayList<>();

        UserRoleExample example = new UserRoleExample();

        example.createCriteria().andUidEqualTo(user.getId());
        List<UserRole> userRoles= userRoleMapper.selectByExample(example);

        for (UserRole userRole : userRoles) {
            Role role=roleMapper.selectByPrimaryKey(userRole.getRid());
            roles.add(role);
        }
        return roles;
    }
}
