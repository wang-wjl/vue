package com.wjl.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Test;

import com.wjl.domain.Customer;
import com.wjl.utils.JpaUtils;

public class JpqlTest {

	/**
	 * 查询
	 * jqpl: from com.wjl.domain.customer
	 * sql:select *from cst_customer;
	 */
	@Test
	public void testFindAll()
	{
		//1.获取EntityManager对象
		EntityManager em = JpaUtils.getEntityManager();
		//2.开启事务
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//3.查询
		String jpql="from com.wjl.domain.Customer";
		Query query = em.createQuery(jpql);//创建Query对象 query对象才是执行jpql的对象
		//发送查询 并封装结果集
		List<Customer> list = query.getResultList();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		//4.提交事务
		tx.commit();
		//5.释放资源
		em.close();
		
	}
	
	/**
	 * sql :select *from cst_customer order by cust_id desc;
	 * jpql: from Customer order by custId desc
	 */
	@Test
	public void testOrders()
	{
		//1.获取EntityManager对象
		EntityManager em = JpaUtils.getEntityManager();
		//2.开启事务
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//3.查询
		String jpql="from Customer order by custId desc";
		Query query = em.createQuery(jpql);//创建Query对象 query对象才是执行jpql的对象
		//发送查询 并封装结果集
		List<Customer> list = query.getResultList();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		//4.提交事务
		tx.commit();
		//5.释放资源
		em.close();
		
	}
	/**
	 * 统计客户的总数
	 * sql：select count(*) from cst_customer;
	 * jpql：select count(custId) from Customer 
	 */
	@Test
	public void testCount()
	{
		//1.获取EntityManager对象
		EntityManager em = JpaUtils.getEntityManager();
		//2.开启事务
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//3.查询
		String jpql="select count(custId) from Customer";
		Query query = em.createQuery(jpql);//创建Query对象 query对象才是执行jpql的对象
		//发送查询 并封装结果集
	    Object result = query.getSingleResult();
		System.out.println(result);
		//4.提交事务
		tx.commit();
		//5.释放资源
		em.close();
		
	}
	
	/**
	 * 分页查询
	 * sql:select *from cst_customer limit 1,2;
	 * jpql:
	 */
	
	@Test
	public void testPaged()
	{
		//1.获取EntityManager对象
		EntityManager em = JpaUtils.getEntityManager();
		//2.开启事务
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//3.查询
		String jpql="from Customer ";
		Query query = em.createQuery(jpql);//创建Query对象 query对象才是执行jpql的对象
		
		//对参数赋值-分页参数
		query.setFirstResult(1);
		query.setMaxResults(2);
		
		
		//发送查询 并封装结果集
		List<Customer> list = query.getResultList();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		//4.提交事务
		tx.commit();
		//5.释放资源
		em.close();
		
	}
	
	/**
	 * 条件查询
	 *    案例：查询客户名称以“XX开头”的客户
	 * sql:select *from cst_customer where cust_address like '深%';
	 * jpql:
	 */
	@Test
	public void testCondition()
	{
		//1.获取EntityManager对象
		EntityManager em = JpaUtils.getEntityManager();
		//2.开启事务
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//3.查询
		String jpql="from Customer where custAddress like ?";
		Query query = em.createQuery(jpql);//创建Query对象 query对象才是执行jpql的对象
		//参数1： 占位符的索引位置（1开始）参数2：取值
		query.setParameter(1, "深%");
		
		//发送查询 并封装结果集
		List<Customer> list = query.getResultList();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		//4.提交事务
		tx.commit();
		//5.释放资源
		em.close();
	}
	
}
