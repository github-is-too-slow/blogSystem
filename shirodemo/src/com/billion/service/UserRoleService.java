package com.billion.service;

import com.billion.entity.User;

/**
 * @author Billion
 * @create 2021/04/03 20:02
 */
public interface UserRoleService {
    public void setRoles(User user, long[] roleIds);
    public void deleteByUser(long userId);
    public void deleteByRole(long roleId);
}
