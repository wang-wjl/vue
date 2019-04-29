package com.wjl.test;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class OneToManyTest {
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private LinkManDao linkManDao;
	
	/**
	 * 保存一个客户 一个联系人
	 *    客户和联系人作为独立的数据保存到数据库中
	 *    联系人外键为空
	 *  原因： 
	 *     实体类中没有配置关系
	 */
	@Test
	@Transactional //配置事务
	@Rollback(value=false)
	public void testAdd()
	{
		Customer customer=new Customer();
		customer.setCustName("百度");
		
		LinkMan linkMan=new LinkMan();
		linkMan.setLkmName("李彦宏");
		
		customerDao.save(customer);
		linkManDao.save(linkMan);
		
	}
	/**
	 *配置客户到联系人的关系
	 *  从客户的角度上：发送两条insert语句 发送一条更新语句更新数据库（更新外键）
	 * 
	 */
	@Test
	@Transactional //配置事务
	@Rollback(value=false)
	public void testAdd2()
	{
		Customer customer=new Customer();
		customer.setCustName("百度");
		
		LinkMan linkMan=new LinkMan();
		linkMan.setLkmName("李彦宏");
		
		customer.getLinkMans().add(linkMan);
		
		customerDao.save(customer);
		linkManDao.save(linkMan);
		
	}
	
	@Test
	@Transactional //配置事务
	@Rollback(value=false)
	public void testAdd3()
	{
		Customer customer=new Customer();
		customer.setCustName("百度");
		
		LinkMan linkMan=new LinkMan();
		linkMan.setLkmName("李彦宏");
		
		/**
		 * 配置联系人到客户的关系（多对一）
		 *   只发送了两个insert语句
		 * 由于配置了联系人到客户的映射关系（多对一）
		 */
		linkMan.setCustomer(customer);
		
		customerDao.save(customer);
		linkManDao.save(linkMan);
		
	}
	
	
	
	/**
	 * 由于的一的一方可以维护外键，发送update语句
	 * 解决：需要在一的一方放弃维护权即可
	 */
	@Test
	@Transactional //配置事务
	@Rollback(value=false)
	public void testAdd4()
	{
		Customer customer=new Customer();
		customer.setCustName("百度");
		
		LinkMan linkMan=new LinkMan();
		linkMan.setLkmName("李彦宏");
		
		linkMan.setCustomer(customer);//由于配置了多的一方到一的一方的关系（保存的时候就对外键赋值）
		customer.getLinkMans().add(linkMan);//由于配置了一的一方到多的一方的关联关系 （发送一条update语句）
		
		customerDao.save(customer);
		linkManDao.save(linkMan);
	}
	
	/**
	 * 级联操作：
	 *    1.需要区分操作主体
	 *    2.需要在操作主体的实体类上，添加级联属性（需要添加到多表映射关系的注解上）
	 *    3.cascade(配置级联)
	 * 
	 * 级联添加
	 *     
	 * 级联删除
	 */
	
	/**
	 * 级联添加，保存一个客户的同时，保存客户的所有联系人
	 *   需要在操作主体的实体类上，配置cascade属性
	 *   
	 */
	@Test
	@Transactional //配置事务
	@Rollback(value=false)
	public void testCascadeAdd()
	{
		Customer customer=new Customer();
		customer.setCustName("百度");
		
		LinkMan linkMan=new LinkMan();
		linkMan.setLkmName("李彦宏");
		
		linkMan.setCustomer(customer);
		
		customerDao.save(customer);
		linkManDao.save(linkMan);
	}
	
	/**
	 * 级联删除
	 *   删除1号客户的同时，删除1号客户的所有联系人
	 *     必须是update
	 */
	@Test
	@Transactional //配置事务
	@Rollback(value=false)
	public void testCascadeRemove()
	{
		Customer customer=customerDao.findOne(1l);
		customerDao.delete(customer);
	}
	
	
}
