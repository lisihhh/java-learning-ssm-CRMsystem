package com.lisi.crm.workbench.service.impl;

import com.lisi.crm.workbench.mapper.ActivityMapper;
import com.lisi.crm.workbench.pojo.Activity;
import com.lisi.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("activityServiceImpl")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    @Qualifier("activityMapper")
    private ActivityMapper activityMapper;
    @Override
    public int saveCreateActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }
}
