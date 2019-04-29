package com.wjl.test;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wjl.dao.CustomerDao;
import com.wjl.dao.LinkManDao;
import com.wjl.entity.Customer;
import com.wjl.entity.LinkMan;

/**
 * 对象导航查询
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class ObjectQueryTest {
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private LinkManDao linkManDao;
	
	//查询一个对象的时候，通过此对象查询所有的关联对象
	@Test
	@Transactional
	@Rollback(false)
	public void testQuery()
	{
		Customer customer = customerDao.getOne(3l);
		System.out.println(customer);
		Set<LinkMan> linkMans = customer.getLinkMans();
		for (LinkMan linkMan : linkMans) {
			System.out.println(linkMan);
		}
	}
	
	/**
	 * 对象导航查询
	 *  默认使用的是延时加载的形式查询的
	 *    调用get方法并不会立即发送查询，而是在使用关联对象的时候才会查询
	 *  延时加载
	 *    修改配置 将延时加载改为立即加载
	 *       fetch 需要配置到多表映射关系的注解上
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void testQuery2()
	{
		Customer customer = customerDao.findOne(3l);
		System.out.println(customer);
		Set<LinkMan> linkMans = customer.getLinkMans();
		for (LinkMan linkMan : linkMans) {
			System.out.println(linkMan);
		}
	}
	
	/**
	 * 从联系人对象导航查询客户
	 * 
	 * 从一方查询多方
	 *    *默认使用延时加载
	 * 从多方查询一方
	 *    *默认使用立即加载
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void testQuery3()
	{
		LinkMan linkMan = linkManDao.findOne(3l);
		System.out.println(linkMan);
		Customer customer = linkMan.getCustomer();
		System.out.println(customer);
	}
}
