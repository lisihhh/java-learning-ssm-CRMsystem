package com.lisi.crm.settings.mapper;

import com.lisi.crm.settings.pojo.User;
import com.lisi.crm.settings.pojo.UserExample;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String id);

    int insert(User row);

    int insertSelective(User row);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("row") User row, @Param("example") UserExample example);

    int updateByExample(@Param("row") User row, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User row);

    int updateByPrimaryKey(User row);

    /**
     * 根据用户名和密码查找用户
     * @param map
     * @return
     */
    User selectByLoginActAndPwd(Map<String, Object> map);

    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAllUsers();
}