package com.yskj.robot.service;

import com.yskj.robot.pojo.User;
import com.yskj.robot.util.Result;

public interface UserService {
    /**
     * 登录
     */
    public Result login(String username,String pwd);

    /**
     * 注册
     */
    public Result register(User user);
}
