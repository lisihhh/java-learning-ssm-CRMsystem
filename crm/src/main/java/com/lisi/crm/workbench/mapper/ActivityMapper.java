package com.lisi.crm.workbench.mapper;

import com.lisi.crm.workbench.pojo.Activity;
import com.lisi.crm.workbench.pojo.ActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityMapper {
    long countByExample(ActivityExample example);

    int deleteByExample(ActivityExample example);

    int deleteByPrimaryKey(String id);

    int insert(Activity row);

    int insertSelective(Activity row);

    List<Activity> selectByExample(ActivityExample example);

    Activity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("row") Activity row, @Param("example") ActivityExample example);

    int updateByExample(@Param("row") Activity row, @Param("example") ActivityExample example);

    int updateByPrimaryKeySelective(Activity row);

    int updateByPrimaryKey(Activity row);

    int insertActivity(Activity activity);
}