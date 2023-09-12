package com.lisi.crm.settings.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lisi.crm.common.constants.Constants;
import com.lisi.crm.common.pojo.ReturnObject;
import com.lisi.crm.common.utils.DateUtils;
import com.lisi.crm.settings.pojo.User;
import com.lisi.crm.settings.service.UserService;
import com.lisi.crm.settings.service.impl.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    /**
     * 转到登陆页
     * @return
     */
    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin() {
        return "settings/qx/user/login";
    }

    /**
     * 登陆操作：
     *      接收前端登陆用户名和密码
     *      判断是否登陆成功
     *      返回登陆结果json信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/settings/qx/user/login.do")
    public String login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpSession session, HttpServletResponse response)
            throws JsonProcessingException {
        //封装前端登陆用户名和密码
        Map<String, Object> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);

        //查询数据库，得到用户
        User user = userService.queryByLoginActAndPwd(map);

        //根据查询结果，判断是否登陆成功，返回登陆结果json信息
        ReturnObject returnObject = new ReturnObject();
        if (user == null) {
            //登陆失败，用户名或密码错误
            returnObject.setLoginSuccessCode(Constants.RETURN_LOGIN_FAIL);
            returnObject.setLoginMessage("用户名或密码错误");
        }else {
            if (DateUtils.formatDateTime(new Date()).compareTo(user.getExpireTime()) > 0) {
                //登陆失败，用户已过期
                returnObject.setLoginSuccessCode(Constants.RETURN_LOGIN_FAIL);
                returnObject.setLoginMessage("用户已过期");
            } else if ("0".equals(user.getLockState())) {
                //登陆失败，用户状态被锁定
                returnObject.setLoginSuccessCode(Constants.RETURN_LOGIN_FAIL);
                returnObject.setLoginMessage("用户状态被锁定");
            } else if (!user.getAllowIps().contains(request.getRemoteAddr())) {
                //登陆失败，ip受限
                returnObject.setLoginSuccessCode(Constants.RETURN_LOGIN_FAIL);
                returnObject.setLoginMessage("ip受限");
            } else {
                //登陆成功
                returnObject.setLoginSuccessCode(Constants.RETURN_LOGIN_SUCCESS);

                //将用户添加到session域，便于前端页面展示
                session.setAttribute("user", user);

                //十天内免登陆
                Cookie cookieAct = new Cookie("loginAct",loginAct);
                Cookie cookiePwd = new Cookie("loginPwd",loginPwd);
                //判断是否勾选“十天内免登陆”
                if ("true".equals(isRemPwd)) {
                    //勾选 十天内免登陆，则将账号密码存在cookie中
                    cookieAct.setMaxAge(10*24*60*60);
                    cookiePwd.setMaxAge(10*24*60*60);
                }else {
                    //未勾选 十天内免登陆，则将cookie中的账号密码删除
                    cookieAct.setMaxAge(0);
                    cookiePwd.setMaxAge(0);
                }
                response.addCookie(cookieAct);
                response.addCookie(cookiePwd);
            }
        }

        //使用jackson将returnObject对象解析成json字符串返回
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(returnObject);
    }

    /**
     * 安全退出操作
     * @return
     */
    @RequestMapping("/settings/qx/user/logout.do")
    public String logout(HttpServletResponse response,HttpSession session) {
        //清空cookie
        Cookie cookieAct = new Cookie("loginAct","0");
        Cookie cookiePwd = new Cookie("loginPwd","0");
        cookieAct.setMaxAge(0);
        cookiePwd.setMaxAge(0);
        response.addCookie(cookieAct);
        response.addCookie(cookiePwd);

        //销毁session
        session.invalidate();

        //跳转到首页
        return "redirect:/";
    }
}
