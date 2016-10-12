package com.wuji1626.framework.support.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wuji1626.framework.support.user.dao.UserDao;
import com.wuji1626.framework.support.user.domain.User;
import com.wuji1626.framework.support.user.service.UserService;
import com.wuji1626.framework.utils.UUIDGenerator;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	public User queryById(int id){
		return userDao.queryById(id);
	}
	public List<User> queryAll(){
		return userDao.queryAll();
	}
	/**
	 * insertOrUpdate User Info
	 */
	public String saveUser(User user) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(user.getUser_id())){
			String user_id = UUIDGenerator.generate();
			user.setUser_id(user_id);
			userDao.insertUser(user);
			return user_id;
		}
		return null;
	}
	@Override
	public List<User> queryByPage(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
