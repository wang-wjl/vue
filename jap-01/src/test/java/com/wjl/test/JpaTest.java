package com.wjl.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.wjl.domain.Customer;
import com.wjl.utils.JpaUtils;

public class JpaTest {
	/**
	 * 测试jpa保存
	 *   案例：保存一个客户到数据库中
	 * jpa的操作步骤
	 *     1.加载配置文件创建工厂 （实体管理器工厂） 对象
	 *     2.通过实体管理类工厂获取实体管理器
	 *     3.获取事务对象 开启事务
	 *     4.完成sql操作 （增删改查）
	 *     5.提交事务（或回滚事务）
	 *     6.释放资源
	 */
	@Test
	public void testSave()
	{
		//1.
		EntityManagerFactory factory = Persistence.createEntityManagerFactory( "myJpa");
		//2.
		EntityManager emanager = factory.createEntityManager();
		//3.
		EntityTransaction tx = emanager.getTransaction(); //获取事务对象
		tx.begin();//开启事务
		//4.
		Customer customer=new Customer();
		customer.setCustName("任正非");
		customer.setCustIndustry("华为");
		customer.setCustAddress("深圳");
		/**
		 * persist 保存
		 * merge   更新
		 * remove  删除
		 * find/getRefrence  根据id查询
		 */
		emanager.persist(customer);
		//5.
		tx.commit();   
		//6.
		emanager.close();
		factory.close();
	}
	
	/**
	 * 根据id查询客户
	 * 使用find查询客户 
	 *     1.查询的对象就是当前客户的对象本身
	 *     2.在调用find方法的时候，就会发送sql语句查询数据库
	 * 使用getReference方法
	 *     1.获取的对象是一个动态代理对象
	 *     2.调用getReference方法不会立即发送sql语句查询数据库
	 *          当调用查询结果对象的时候，才会发送查询sql语句 什么时候用什么时候发送sql语句查询数据库
	 */
	@Test
	public void update()
	{
		//1.通过工具类获取EntityManager
		EntityManager em = JpaUtils.getEntityManager();
		//2.开启事务
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//3.sql操作
		Customer customer = em.find(Customer.class, 2l);
		System.out.println("修改前:"+customer);
		customer.setCustName("李彦宏2");
		em.merge(customer);
		
		Customer customer2 = em.getReference(Customer.class, 2l);
		System.out.println("修改后:"+customer2);
		//4.提交事务
		tx.commit();
		//5.释放资源
		em.close();
	}
	
	//删除
	@Test
	public void testRemove()
	{
		//1.通过工具类获取EntityManager
		EntityManager em = JpaUtils.getEntityManager();
		//2.开启事务
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Customer customer = em.getReference(Customer.class, 2l);
		em.remove(customer);
		//4.提交事务
		tx.commit();
		//5.释放资源
		em.close();
		
	}
	
}
