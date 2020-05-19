package com.captcha.spring.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.captcha.spring.mvc.model.User;
import com.captcha.spring.mvc.service.UserService;

@Controller
public class UserRegistrationController {

  @Autowired
  public UserService userService;

  @RequestMapping(value = "/registration", method = RequestMethod.GET)
  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("userRegistration");
    mav.addObject("user", new User());
    return mav;
  }

  @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, HttpSession session,
      @Valid @ModelAttribute("user") User user, BindingResult br) throws Exception {

    String captcha = session.getAttribute("captcha_security").toString();
    String verifyCaptcha = request.getParameter("captcha");
    ModelAndView mav = null;
    if (br.hasErrors()) {
      mav = new ModelAndView("userRegistration");
      return mav;
    }
    if (!captcha.equals(verifyCaptcha)) {
      mav = new ModelAndView("userRegistration");
      mav.addObject("message", "invalid captcha");
      return mav;
    }
    try {
      userService.register(user);
      session.setAttribute("USER_SESSION", user.getUsername());
    } catch (Exception e) {
      throw new Exception(e);

    }
    mav = new ModelAndView("welcome");
    return mav.addObject("message", "User Registration Success");

  }

  @ExceptionHandler(Exception.class)
  public ModelAndView handleAllException(Exception ex) {
    ModelAndView model = new ModelAndView("error");
    model.addObject("errorMessage", ex.getCause().getMessage());
    return model;

  }

}
