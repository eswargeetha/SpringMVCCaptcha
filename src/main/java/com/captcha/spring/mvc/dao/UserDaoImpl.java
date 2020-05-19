package com.captcha.spring.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.captcha.spring.mvc.h2.config.DataSourceConfiguration;
import com.captcha.spring.mvc.model.User;

public class UserDaoImpl implements UserDao {

  @Autowired
  private DataSourceConfiguration dataSourceConfiguration;

  public void register(User user) throws Exception {
    Connection connection = null;
    try {
      final DataSource dataSource = dataSourceConfiguration.dataSource();
      connection = dataSource.getConnection();
      final Statement statement = connection.createStatement();

      String sql = "insert into users (username, password, name, email) values('" + user.getUsername() + "', '"
          + user.getPassword() + "', '" + user.getName() + "','" + user.getEmail() + "')";
      statement.executeUpdate(sql);
    } catch (SQLException e) {
      throw new Exception(e);
    } finally {
      connection.close();
    }
  }

  public User validateUser(User user) {

    User loginUser = null;
    ResultSet rs = null;
    try {
      final DataSource dataSource = dataSourceConfiguration.dataSource();
      final Connection connection = dataSource.getConnection();
      final Statement statement = connection.createStatement();

      String sql = "select * from users where username='" + user.getUsername() + "' and password='" + user.getPassword()
          + "'";

      rs = statement.executeQuery(sql);
      if (rs.next()) {
        loginUser = new User();
        loginUser.setUsername(rs.getString("username"));
        loginUser.setName(rs.getString("name"));
        loginUser.setPassword(rs.getString("password"));
        loginUser.setEmail(rs.getString("email"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return loginUser;
  }

  public User getUser(String loginUser) {

    User user = null;
    ResultSet rs = null;
    try {
      final DataSource dataSource = dataSourceConfiguration.dataSource();
      final Connection connection = dataSource.getConnection();
      final Statement statement = connection.createStatement();

      String sql = "select * from users where username='" + loginUser + "'";

      rs = statement.executeQuery(sql);
      if (rs.next()) {
        user = new User();
        user.setUsername(rs.getString("username"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return user;
  }

  public void updateUser(User user, String loginUser) throws Exception {
    Connection connection = null;
    try {
      final DataSource dataSource = dataSourceConfiguration.dataSource();
      connection = dataSource.getConnection();
      final Statement statement = connection.createStatement();

      String sql = "update users set username = '" + user.getUsername() + "', password = '" + user.getPassword()
          + "', name = '" + user.getName() + "', email = '" + user.getEmail() + "' where username = '" + loginUser
          + "'";

      statement.executeUpdate(sql);

    } catch (Exception e) {
      throw new Exception(e);
    } finally {
      connection.close();
    }

  }

}
