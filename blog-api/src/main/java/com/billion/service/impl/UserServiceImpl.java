package com.billion.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.billion.entity.User;
import com.billion.mapper.user.UserMapper;
import com.billion.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Billion
 * @create 2021/04/04 17:19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService{
}
