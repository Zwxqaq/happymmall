package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;

public interface IUserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse register(User user);

    ServerResponse<String> checkValid(String checkData, String type);

    ServerResponse<String> getQuestion(String username);

    ServerResponse checkAnswer(String username, String question, String answer);

    ServerResponse forgetResetPass(String username, String passwordNew, String forgetToken);

    ServerResponse resetPass(String passwordOld, String passwordNew, User user);

    ServerResponse<User> updateInfo(User user);

    ServerResponse<PageInfo> list(int pageNum,int pageSize);

    ServerResponse checkeAdmin(User user);
}
