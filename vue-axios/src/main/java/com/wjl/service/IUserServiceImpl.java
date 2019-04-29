package com.wjl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjl.dao.IUserDao;
import com.wjl.domain.User;
@Service
public class IUserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userDao;
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findById(Integer userId) {
		return userDao.findById(userId);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	
}
