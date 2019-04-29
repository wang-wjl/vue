package com.wjl.service;

import java.util.List;


import com.wjl.domain.User;

/**
 * 用户的业务层
 * @author Administrator
 *
 */
public interface IUserService {
	List<User> findAll();
	User findById(Integer userId);
	void updateUser(User user);
}
