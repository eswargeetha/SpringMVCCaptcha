package com.captcha.spring.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.captcha.spring.mvc.model.User;
import com.captcha.spring.mvc.service.UserService;

@Controller
public class AccountUpdateController {

  @Autowired
  public UserService userService;

  @RequestMapping(value = "/userUpdate", method = RequestMethod.GET)
  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
    ModelAndView mav = new ModelAndView("accountUpdate");
    String loginUser = (String) session.getAttribute("USER_SESSION");
    User user = userService.getUser(loginUser);
    mav.addObject("user", user);
    return mav;
  }

  @RequestMapping(value = "/updateProcess", method = RequestMethod.POST)
  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, HttpSession session,
      @Valid @ModelAttribute("user") User userDetail, BindingResult br) throws Exception {

    String loginUser = (String) session.getAttribute("USER_SESSION");
    String captcha = session.getAttribute("captcha_security").toString();
    String verifyCaptcha = request.getParameter("captcha");
    ModelAndView mav = null;

    if (br.hasErrors()) {
      mav = new ModelAndView("userRegistration");
      return mav;
    }
    if (!userDetail.getPassword().equals(userDetail.getConfirmPassword())) {
      mav = new ModelAndView("userRegistration");
      mav.addObject("message", "Password and Confirm Password should be same");
      return mav;
    }
    if (!captcha.equals(verifyCaptcha)) {
      mav = new ModelAndView("userRegistration");
      mav.addObject("message", "invalid captcha");
      return mav;
    }

    try {
      userService.updateUser(userDetail, loginUser);
      session.setAttribute("USER_SESSION", userDetail.getUsername());
    } catch (Exception e) {
      throw new Exception(e);
    }
    mav = new ModelAndView("welcome");
    mav.addObject("message", "Account Update Success");
    return mav;
  }

  @RequestMapping(value = "/tutorials", method = RequestMethod.GET)
  public String getTutorials(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
    return "/tutorials";
  }

  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  public String getAdminPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
    return "/admin";
  }

}
