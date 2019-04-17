package com.mmall.service.impl;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.common.TokenCache;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.util.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service("iUserService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    public ServerResponse<User> login(String username,String password) {
        int res = userMapper.checkUsername(username);
        if (res == 0) {
            return ServerResponse.createByErrorMsg("can't find the username");
        }
        if (StringUtils.isBlank(password)) {
            return ServerResponse.createByErrorMsg("input the password plz");
        }
        String pass = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.checkLoginInfo(username, pass);
        if (user == null) {
            return ServerResponse.createByErrorMsg("wrong pass..");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("login success", user);
    }

    public ServerResponse checkValid(String checkData, String type) {
        if (StringUtils.isNotBlank(type)) {
            if (Const.USERNAME.equals(type)) {
                int res = userMapper.checkUsername(checkData);
                if (res > 0) {
                    return ServerResponse.createByErrorMsg("用户已存在");
                }
                type = "用户";
            }
            if(Const.EMAIL.equals(type)) {
                int res = userMapper.checkEmail(checkData);
                if (res > 0) {
                    return ServerResponse.createByErrorMsg("邮箱已存在");
                }
                type = "邮箱";
            }
        } else {
            return ServerResponse.createByErrorMsg(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("验证").append(type).append("通过");
        return ServerResponse.createBySuccessMsg(buffer.toString());
    }


    @Override
    public ServerResponse<String> getQuestion(String username) {
        String question = userMapper.selectQuestion(username);
        if (StringUtils.isBlank(question)) {
            return ServerResponse.createByErrorMsg("该用户未设置找回密码问题");
        }
        return ServerResponse.createBySuccess(question);
    }

    @Override
    public ServerResponse checkAnswer(String username, String question, String answer) {
        int res = userMapper.checkAnswer(username, question, answer);
        if (res == 0) {
            return ServerResponse.createByErrorMsg("问题答案错误");
        }
        String token = UUID.randomUUID().toString();
        TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, token);
        return ServerResponse.createBySuccess(token);
    }

    @Override
    public ServerResponse forgetResetPass(String username, String password, String forgetToken) {
        String cacheToken = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        if (StringUtils.isBlank(cacheToken)) {
            return ServerResponse.createByErrorMsg("token已经失效");
        }
        if (StringUtils.equals(cacheToken, forgetToken)) {
            String passwordNew = MD5Util.MD5EncodeUtf8(password);
            int res = userMapper.updateByNameAndPass(username,passwordNew);
            if (res != 0) {
                return ServerResponse.createBySuccessMsg("修改密码成功");
            }
        }
        return ServerResponse.createByErrorMsg("修改密码操作失效");
    }

    @Override
    public ServerResponse resetPass(String passwordOld, String passwordNew, User user) {
        if (user == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        int res = userMapper.checkPass(user.getUsername(), MD5Util.MD5EncodeUtf8(passwordOld));
        if (res == 0){
            return ServerResponse.createByErrorMsg("旧密码输入错误");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        res = userMapper.updateByPrimaryKeySelective(user);
        if (res > 0) {
            return ServerResponse.createBySuccessMsg("修改密码成功");
        }
        return ServerResponse.createByErrorMsg("修改密码操作失效");
    }

    @Override
    public ServerResponse<User> updateInfo(User user) {
        ServerResponse res = checkValid(user.getEmail(), Const.EMAIL);
        if (!res.isSuccess()) {
            return ServerResponse.createByErrorMsg("邮箱已被占用，请更换邮箱");
        }
        User updUser = new User();
        updUser.setId(user.getId());
        updUser.setEmail(user.getEmail());
        updUser.setPhone(user.getPhone());
        updUser.setQuestion(user.getQuestion());
        updUser.setAnswer(user.getAnswer());
        int resCode = userMapper.updateByPrimaryKeySelective(updUser);
        if (resCode > 0) {
            return ServerResponse.createBySuccess("更新个人信息成功", updUser);

        }
        return ServerResponse.createByErrorMsg("更新个人信息失败");
    }
    //    ####9.登录状态更新个人信息 /user/update_information.do
//
//    request
//
//            email,phone,question,answer
//
//    response
//
//            success
//
//    {
//        "status": 0,
//            "msg": "更新个人信息成功"
//    }
//
//    fail
//
//    {
//        "status": 1,
//            "msg": "用户未登录"
//    }


    //        username,password,email,phone,question,answer

    @Override
    public ServerResponse register(User user) {
        ServerResponse response = this.checkValid(user.getUsername(), Const.USERNAME);
        if (!response.isSuccess()) {
            return response;
        }
        response = this.checkValid(user.getEmail(), Const.EMAIL);
        if (!response.isSuccess()) {
            return response;
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int res = userMapper.insert(user);
        if (res == 0) {
            return ServerResponse.createByErrorMsg("注册失败");
        }
        return ServerResponse.createBySuccessMsg("注册成功");
    }











































}
