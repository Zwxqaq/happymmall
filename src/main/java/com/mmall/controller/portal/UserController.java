package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;



@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse register(User user) {
        return iUserService.register(user);
    }

    @RequestMapping(value = "check_valid.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String checkData, String type) {
       return iUserService.checkValid(checkData, type);
    }

    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMsg("user no login...");
    }


    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse forgetGetQuestion(String username) {
        if (StringUtils.isBlank(username)) {
            return ServerResponse.createByErrorMsg(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        ServerResponse response = iUserService.checkValid(username, Const.USERNAME);
        if (response.isSuccess()) {
            return ServerResponse.createByErrorMsg("用户不存在");
        }
        return iUserService.getQuestion(username);
    }


    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse forgetCheckAnswer(String username, String question, String answer) {
        return iUserService.checkAnswer(username, question, answer);
    }

    @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse forgetResetPass(String username, String passwordNew, String forgetToken) {
        return iUserService.forgetResetPass(username, passwordNew, forgetToken);
    }


    @RequestMapping(value = "reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse resetPass(HttpSession session,String passwordOld, String passwordNew) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return iUserService.resetPass(passwordOld, passwordNew, user);
    }

    @RequestMapping(value = "update_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateInfo(HttpSession session, User user) {
        User curUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (curUser == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        user.setId(curUser.getId());
        user.setUsername(curUser.getUsername());
        ServerResponse<User> response = iUserService.updateInfo(user);
        if (response.isSuccess()) {
            response.getData().setUsername(curUser.getUsername());
            response.getData().setPassword(StringUtils.EMPTY);
            response.getData().setRole(curUser.getRole());
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    @RequestMapping(value = "get_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getInfo(HttpSession session) {
        User curUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (curUser == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return ServerResponse.createBySuccess(curUser);
    }
//    ####10.获取当前登录用户的详细信息，并强制登录 /user/get_information.do
//
//    request
//
//            无参数
//
//    response
//
//            success
//
//    {
//        "status": 0,
//            "data": {
//        "id": 1,
//                "username": "admin",
//                "password": "",
//                "email": "admin@163.com",
//                "phone": "13800138000",
//                "question": "question",
//                "answer": "answer",
//                "role": 1,
//                "createTime": 1478422605000,
//                "updateTime": 1491305256000
//    }
//    }
//
//    fail
//
//    {
//        "status": 10,
//            "msg": "用户未登录,无法获取当前用户信息,status=10,强制登录"
//    }







































































}

