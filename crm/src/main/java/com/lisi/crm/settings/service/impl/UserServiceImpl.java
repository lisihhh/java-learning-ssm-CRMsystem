package com.lisi.crm.settings.service.impl;

import com.lisi.crm.settings.mapper.UserMapper;
import com.lisi.crm.settings.pojo.User;
import com.lisi.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;

    @Override
    public User queryByLoginActAndPwd(Map<String, Object> map) {
        return userMapper.selectByLoginActAndPwd(map);
    }

    @Override
    public List<User> queryAllUsers() {
        return userMapper.selectAllUsers();
    }
}
