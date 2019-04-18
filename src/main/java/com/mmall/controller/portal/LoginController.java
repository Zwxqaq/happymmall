package com.mmall.controller.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @RequestMapping("login.do")
    public ModelAndView log(HttpServletRequest request) {
        return new ModelAndView("login");
    }

    @RequestMapping("index.do")
    public ModelAndView lindex(HttpServletRequest request) {
        return new ModelAndView("index");
    }
}
