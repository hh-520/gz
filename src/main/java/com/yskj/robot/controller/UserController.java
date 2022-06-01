package com.yskj.robot.controller;

import com.yskj.robot.common.Consts;
import com.yskj.robot.pojo.User;
import com.yskj.robot.service.UserService;
import com.yskj.robot.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/portal")
public class UserController {

//    @Value("${jdbc.url}")
//    private String jdbcUrl;
//
//    @RequestMapping("/test")
//    public String test(){
//
//        return jdbcUrl;
//    }

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/user/login.do")
    public Result login(String username, String password, HttpSession session){
        /*User user = new User();
        user.setUsername(username);
        user.setPassword(pwd);
        return Result.success(user);*/
        Result result = userService.login(username,password);
        if (result.isSuccess()){
            session.setAttribute(Consts.CURRENT_USER,result.getData());
        }
        return result;
    }
    @RequestMapping("user/register.do")
    public Result register(User user){
        return userService.register(user);
    }
}
