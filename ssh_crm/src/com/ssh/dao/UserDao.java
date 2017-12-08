package com.ssh.dao;

import java.util.List;

import com.ssh.entity.User;

public interface UserDao {

	User loginUser(User user);

	List<User> findAll();
  
}
