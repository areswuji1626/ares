package com.wuji1626.framework.support.user.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wuji1626.framework.support.user.dao.UserDao;
import com.wuji1626.framework.support.user.domain.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	// 查询所全部户信息
	private static final String QUERY_ALL = "queryAll";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public User queryById(int id) {
		return (User) sqlSessionTemplate.selectOne("queryById", id);
	}

	public List<User> queryAll() {
		List<User> users = sqlSessionTemplate.selectList(QUERY_ALL);
		return users;
	}

	public void insertUser(User user) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert("insertUser",user);
	}

}
