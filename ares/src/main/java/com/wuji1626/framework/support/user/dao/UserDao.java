package com.wuji1626.framework.support.user.dao;

import java.util.List;

import com.wuji1626.framework.support.user.domain.User;

public interface UserDao {
	public User queryById(int id);
	public List<User> queryAll();  
	public void insertUser(User user);
}
