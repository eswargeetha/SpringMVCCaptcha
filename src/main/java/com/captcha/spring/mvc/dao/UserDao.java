package com.captcha.spring.mvc.dao;

import com.captcha.spring.mvc.model.User;

public interface UserDao {

  void register(User user) throws Exception;

  User validateUser(User user);

  User getUser(String loginUser);
  
  void updateUser(User user, String loginUser) throws Exception;
}
