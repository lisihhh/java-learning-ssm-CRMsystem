package com.lisi.crm.workbench.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lisi.crm.common.constants.Constants;
import com.lisi.crm.common.pojo.ReturnObject;
import com.lisi.crm.common.utils.DateUtils;
import com.lisi.crm.common.utils.UUIDUtils;
import com.lisi.crm.settings.pojo.User;
import com.lisi.crm.settings.service.UserService;
import com.lisi.crm.workbench.pojo.Activity;
import com.lisi.crm.workbench.service.ActivityService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class ActivityController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("activityServiceImpl")
    private ActivityService activityService;

    /**
     * 转到市场活动首页
     * @param request
     * @return
     */
    @RequestMapping("/workbench/activity/toIndex.do")
    public String toIndex(HttpServletRequest request) {
        //获取所有用户
        List<User> users = userService.queryAllUsers();
        //将用户加到request域中
        request.setAttribute("users",users);
        return "workbench/activity/index";
    }

    @ResponseBody
    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    public String saveCreateActivity(Activity activity, HttpSession session)
            throws JsonProcessingException {
        //封装参数
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formatDateTime(new Date()));
        activity.setCreateBy(user.getId());

        //返回的相关信息
        ReturnObject returnObject = new ReturnObject();

        //插入市场活动
        try {
            //往数据库中插入该市场活动
            int res = activityService.saveCreateActivity(activity);
            if (res == 1) {
                //插入成功
                returnObject.setSuccessCode(Constants.RETURN_SUCCESS);
            }else {
                //插入失败
                returnObject.setSuccessCode(Constants.RETURN_FAIL);
                returnObject.setMessage("系统繁忙，请稍后重试");
            }
        }catch (Exception e) {
            //插入失败
            e.printStackTrace();
            returnObject.setSuccessCode(Constants.RETURN_FAIL);
            returnObject.setMessage("系统繁忙，请稍后重试");
        }

        //使用jackson将returnObject对象解析成json字符串返回
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(returnObject);
    }
}
