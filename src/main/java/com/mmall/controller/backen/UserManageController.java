package com.mmall.controller.backen;

import com.github.pagehelper.PageInfo;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/user/")
public class UserManageController {
    @Autowired
    IUserService iUserService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse login(HttpSession session,String username, String password) {
        ServerResponse<User> res = iUserService.login(username, password);
        if (res.isSuccess()) {
            int role = res.getData().getRole();
            if (role == Const.Role.ROLE_ADMIN) {
                return res;
            }
        }
        return ServerResponse.createByErrorMsg("登录失败");
    }

    @RequestMapping(value = "list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse list(HttpSession session, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMsg(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        int role = user.getRole();
        if (role == Const.Role.ROLE_ADMIN) {
            ServerResponse<PageInfo> res = iUserService.list(pageNum, pageSize);
            return res;
        } else {
            return ServerResponse.createByErrorMsg("没有权限");
        }
    }
//    pageSize(default=10)
//    pageNum(default=1)
































































}
