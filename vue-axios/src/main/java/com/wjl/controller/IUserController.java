package com.wjl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjl.domain.User;
import com.wjl.service.IUserService;

@Controller
@RequestMapping("/user")
@ResponseBody
public class IUserController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/findAll")
	public List<User> findAll()
	{
		return userService.findAll();
	}
	@RequestMapping("/findById")
	public User findById(Integer id)
	{
		return userService.findById(id);
	}
	/**
	 * 更新
	 * @param user
	 */
	@RequestMapping("/updateUser")
	public void updateUser(@RequestBody User user)
	{
		userService.updateUser(user);
	}
	
}
