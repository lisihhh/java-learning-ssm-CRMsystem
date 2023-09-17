package com.lisi.crm.workbench.service;

import com.lisi.crm.workbench.pojo.Activity;

public interface ActivityService {
    /**
     * 保存创建的市场活动
     * @param activity
     * @return
     */
    int saveCreateActivity(Activity activity);
}
