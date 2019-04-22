package com.mmall.controller.portal;

import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequestMapping("/user/")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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

    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createBySuccessMsg("退出成功");
        }
        return ServerResponse.createByErrorMsg("服务端异常");
    }
//    param += "&email=" + email;
//    param += "&id=" + id;
//    param += "&phone=" + phone;
//    param += "&question=" + question;
//    param += "&username=" + username;
//    param += "&role=" + role;
    @RequestMapping(value = "loginDetail.do")
    @ResponseBody
    public ModelAndView loginDetail(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String question = request.getParameter("question");
        String username = request.getParameter("username");
        String role = request.getParameter("role");
        Map<String,String> map = Maps.newHashMap();
        map.put("id", id);
        map.put("email", email);
        map.put("phone", phone);
        map.put("question", question);
        map.put("username", username);
        map.put("role", role);
        logger.info("id:{},email:{},phone:{}",id , email, phone);
        return new ModelAndView("loginDetail", map);
    }








































































}

