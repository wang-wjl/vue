package com.wjl.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wjl.dao.CustomerDao;
import com.wjl.entity.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class CustomerTest {
	@Autowired
	private CustomerDao customerDao;
	
	@Test
	public void test()
	{
		//查询所有
		List<Customer> list = customerDao.findAll();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		//根据id查询
		Customer c1 = customerDao.findOne(2l);
		System.out.println("findone:"+c1);
	}
	/**
	 * save 保存或者更新
	 *   根据传递的对象是否存在主键id 如果没有id主键属性 保存
	 *   存在id主键属性，根据id查询数据，更新数据
	 */
	@Test
	public void testSave()
	{
		Customer customer = new Customer();
		customer.setCustName("李彦宏");
		customer.setCustLevel("VIP");
		customer.setCustAddress("北京");
		customerDao.save(customer);
	}
	
	@Test
	public void testUpdate()
	{
		Customer customer = customerDao.findOne(1l);
		customer.setCustName("马云的阿里");
		customer.setCustLevel("VIP");
		customer.setCustAddress("杭州阿里巴巴");
		customerDao.save(customer);
	}
	
	/**
	 * 删除
	 */
	@Test
	public void testDelete()
	{
		customerDao.delete(3l);
	}
	
	/**
	 * 总条数
	 */
	@Test
	public void testCount()
	{
		long count = customerDao.count();
		System.out.println(count);
	}
	
	/**
	 * exists()方法
	 */
	@Test
	public void testExists()
	{
		//查询id为3的客户是否存在
		boolean exists = customerDao.exists(3l);
		System.out.println(exists);
	}
	
	/**
	 * 根据id从数据查询
	 *   @Transactional:保证getOne正常执行
	 * findOne
	 *     em.find()       立即加载
	 * getOne
	 *     em.getreference 延时加载
	 *     返回的是一个客户的动态代理对象
	 *     什么时候用，什么时候查询
	 */
	@Test
	@Transactional
	public void testgetOne()
	{
		//getOne  当id不存在会报错
		Customer customer = customerDao.getOne(3l);
		System.out.println(customer);
	}
	
	
	
	

}
