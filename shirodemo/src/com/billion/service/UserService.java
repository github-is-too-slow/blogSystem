package com.billion.service;

import com.billion.entity.User;

import java.util.List;

/**
 * @author Billion
 * @create 2021/04/03 19:43
 */
public interface UserService {
    public String getPassword(String name);
    public User getByName(String name);

    public List<User> list();
    public void add(User user);
    public void delete(Long id);
    public User get(Long id);
    public void update(User user);
}
