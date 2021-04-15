package com.billion.service;

import com.billion.entity.Permission;
import com.billion.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * @author Billion
 * @create 2021/04/03 19:45
 */
public interface PermissionService {
    public Set<String> listPermissions(String userName);

    public List<Permission> list();
    public void add(Permission permission);
    public void delete(Long id);
    public Permission get(Long id);
    public void update(Permission permission);

    public List<Permission> list(Role role);
}
