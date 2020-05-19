package com.captcha.spring.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.captcha.spring.mvc.model.User;
import com.captcha.spring.mvc.service.UserService;

@Controller
public class LoginController {

  @Autowired
  public UserService userService;

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("login");
    mav.addObject("user", new User());
    return mav;
  }

  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, HttpSession session,
      @ModelAttribute("user") User userDetail) {

    ModelAndView mav = null;
    String captcha = session.getAttribute("captcha_security").toString();
    String verifyCaptcha = request.getParameter("captcha");
    if (!captcha.equals(verifyCaptcha)) {
      mav = new ModelAndView("login");
      mav.addObject("message", "invalid captcha");
      return mav;
    }

    User user = userService.validateUser(userDetail);
    if (null != user) {
      mav = new ModelAndView("welcome");
      mav.addObject("firstname", user.getName());
      session.setAttribute("USER_SESSION", user.getUsername());
    } else {
      mav = new ModelAndView("login");
      mav.addObject("message", "Username or Password is wrong!!");
    }

    return mav;
  }

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public String logoff(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
    session.invalidate();
    return "../home";
  }

}
