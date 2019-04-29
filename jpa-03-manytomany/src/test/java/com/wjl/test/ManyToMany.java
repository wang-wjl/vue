package com.wjl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wjl.dao.RoleDao;
import com.wjl.dao.UserDao;
import com.wjl.entity.Role;
import com.wjl.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class ManyToMany {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	
	/**
	 * 多对多放弃维护权，被动的一方放弃
	 *    角色一方放弃
	 */
	@Test
	@Transactional
	@Rollback(value=false)
	public void test()
	{
		User user=new User();
		user.setUserName("Tom");
		
		Role role=new Role();
		role.setRoleName("java程序员");
		
		//配置用户到角色的关系,可以对中间表的数据进行维护
		user.getRoles().add(role);
		//配置角色到用户的关系 
		role.getUsers().add(user);//主键冲突
		
		userDao.save(user);
		roleDao.save(role);
		
	}
	
	/**
	 * 级联添加
	 *    保存一个用户同时保存用户的关联角色
	 */
	
	@Test
	@Transactional
	@Rollback(value=false)
	public void testCascadeAdd()
	{
		User user=new User();
		user.setUserName("Tom");
		
		Role role=new Role();
		role.setRoleName("java程序员");
		
		user.getRoles().add(role);
		role.getUsers().add(user);
		
		userDao.save(user);
	}
	
	/**
	 * 级联删除
	 */
	@Test
	@Transactional
	@Rollback(value=false)
	public void testCascadeRemove()
	{
		User user = userDao.findOne(1l);
		userDao.delete(user);
		
	}
}
