package com.lisi.crm.workbench.web.controller;

import com.lisi.crm.settings.pojo.User;
import com.lisi.crm.settings.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ActivityController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping("/workbench/activity/toIndex.do")
    public String toIndex(HttpServletRequest request) {
        //获取所有用户
        List<User> users = userService.queryAllUsers();
        //将用户加到request域中
        request.setAttribute("users",users);
        return "workbench/activity/index";
    }
}
