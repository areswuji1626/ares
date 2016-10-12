package com.wuji1626.framework.support.user.service;

import java.util.List;

import com.wuji1626.framework.support.user.domain.User;


public interface UserService {

	public User queryById(int id);
	public List<User> queryAll();
	public List<User> queryByPage(User user);
	public String saveUser(User user);
	
}
