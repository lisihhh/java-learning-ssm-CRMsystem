package com.lisi.crm.settings.service;

import com.lisi.crm.settings.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User queryByLoginActAndPwd(Map<String, Object> map);

    List<User> queryAllUsers();
}
