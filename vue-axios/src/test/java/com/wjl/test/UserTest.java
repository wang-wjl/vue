package com.wjl.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wjl.domain.User;
import com.wjl.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class UserTest {
	@Autowired
	private IUserService userService;
	
	@Test
	public void test1()
	{
		List<User> list = userService.findAll();
		System.out.println(list);
	}
	@Test
	public void test2()
	{
		User user = userService.findById(1);
		System.out.println(user);
	}
	@Test
	public void test3()
	{
		User user = userService.findById(1);
		System.out.println("修改前:"+user);
		user.setAge(66);
		userService.updateUser(user);
		
		User u=userService.findById(1);
		System.out.println("修改后:"+u);
	}
}
