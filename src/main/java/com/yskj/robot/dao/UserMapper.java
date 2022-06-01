package com.yskj.robot.dao;

import com.yskj.robot.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 判断用户名是否存在
     */
    int loginByUsername(@Param("username") String username);
    /**
     * 根据用户名和密码查询
     */
    User login(@Param("username")String username,@Param("password")String password);

    /**
     * 判断邮箱是否存在
     */
    int registerByEmail(@Param("email") String email);
}