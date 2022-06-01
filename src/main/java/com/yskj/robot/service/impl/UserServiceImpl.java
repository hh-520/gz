package com.yskj.robot.service.impl;

import com.yskj.robot.common.Consts;
import com.yskj.robot.common.ResponseCode;
import com.yskj.robot.dao.UserMapper;
import com.yskj.robot.pojo.User;
import com.yskj.robot.service.UserService;
import com.yskj.robot.util.DateUtil;
import com.yskj.robot.util.MD5Utils;
import com.yskj.robot.util.Result;
import com.yskj.robot.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Result login(String username, String password){
        // 用户名密码非空判断
        if(StringUtils.isBlank(username)){
            return Result.fail(ResponseCode.USERNAME_NOT_EMPT.getCode(),ResponseCode.USERNAME_NOT_EMPT.getMsg());
        }
        if (password==null||password.equals("")){
            return Result.fail(ResponseCode.PWD_NOT_EMPTY.getCode(),ResponseCode.PWD_NOT_EMPTY.getMsg());
        }
        // 查看用户名是否存在
        Integer count = userMapper.loginByUsername(username);
        if(count == 0){
            return Result.fail(ResponseCode.USERNAME_NOT_EXISTS.getCode(), ResponseCode.USERNAME_NOT_EXISTS.getMsg());
        }
        try{
            //根据用户名和密码查询
            User user = userMapper.login(username,MD5Utils.getMD5(password));
            if(user==null){
                return Result.fail(ResponseCode.PWD_ERROR.getCode(),ResponseCode.PWD_ERROR.getMsg());
            }
            //返回结果
            return Result.success(convert(user));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.success();
    }

    private UserVo convert(User user){
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUsername(user.getUsername());
        userVo.setEmail(user.getEmail());
        userVo.setPhone(user.getPhone());
        userVo.setCreateTime(DateUtil.Date2String(user.getCreateTime()));
        userVo.setUpdateTime(DateUtil.Date2String(user.getUpdateTime()));

        return userVo;
    }

   @Override
    public Result register(User user) {
        if (user==null){
            return Result.fail(ResponseCode.PARAMTER_NOT_EMPTY.getCode(),ResponseCode.PARAMTER_NOT_EMPTY.getMsg());
        }
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        String question = user.getQuestion();
        String answer =user.getAnswer();
        String phone = user.getPhone();

        // 非空判断
        if(username==null || username.equals("")){
            return Result.fail(ResponseCode.USERNAME_NOT_EMPT.getCode(),ResponseCode.USERNAME_NOT_EMPT.getMsg());
        }
        if (password==null||password.equals("")){
            return Result.fail(ResponseCode.PWD_NOT_EMPTY.getCode(),ResponseCode.PWD_NOT_EMPTY.getMsg());
        }
        if (email==null||email.equals("")){
            return Result.fail(ResponseCode.EMAIL_NOT_EMPTY.getCode(),ResponseCode.EMAIL_NOT_EMPTY.getMsg());
        }
        if (question==null||question.equals("")){
            return Result.fail(ResponseCode.QUESTION_NOT_EMPTY.getCode(),ResponseCode.QUESTION_NOT_EMPTY.getMsg());
        }
        if (answer==null||answer.equals("")){
            return Result.fail(ResponseCode.ANSWER_NOT_EMPTY.getCode(),ResponseCode.ANSWER_NOT_EMPTY.getMsg());
        }
        if (phone==null||phone.equals("")){
            return Result.fail(ResponseCode.PHONE_NOT_EMPTY.getCode(),ResponseCode.PHONE_NOT_EMPTY.getMsg());
        }
        // 判断用户名是否存在
        Integer count = userMapper.loginByUsername(username);
        if (count > 0){
            return Result.fail(ResponseCode.USERNAME_EXISTS.getCode(),ResponseCode.USERNAME_EXISTS.getMsg());
        }
        // 判断邮箱是否存在
        Integer email_count = userMapper.registerByEmail(email);
        if (email_count > 0){
            return Result.fail(ResponseCode.EMAIL_EXISTS.getCode(),ResponseCode.EMAIL_EXISTS.getMsg());
        }
        // 注册
        try {
            user.setPassword(MD5Utils.getMD5(user.getPassword()));
            user.setRole(Consts.NORMAL_USER); // 设置用户的角色
            int result = userMapper.insert(user);
            if (result == 0){
                return Result.fail(ResponseCode.REGISTER_FAIL.getCode(),ResponseCode.REGISTER_FAIL.getMsg());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.success(user);
    }
}
