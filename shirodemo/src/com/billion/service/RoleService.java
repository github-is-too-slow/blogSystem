package com.billion.service;

import com.billion.entity.Role;
import com.billion.entity.User;

import java.util.List;
import java.util.Set;

/**
 * @author Billion
 * @create 2021/04/03 19:43
 */
public interface RoleService {
    public Set<String> listRoleNames(String userName);
    public List<Role> listRoles(String userName);
    public List<Role> listRoles(User user);

    public List<Role> list();
    public void add(Role role);
    public void delete(Long id);
    public Role get(Long id);
    public void update(Role role);
}
