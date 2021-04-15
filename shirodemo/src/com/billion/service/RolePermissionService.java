package com.billion.service;

import com.billion.entity.Role;

/**
 * @author Billion
 * @create 2021/04/03 20:03
 */
public interface RolePermissionService {
    public void setPermissions(Role role, long[] permissionIds);
    public void deleteByRole(long roleId);
    public void deleteByPermission(long permissionId);
}
