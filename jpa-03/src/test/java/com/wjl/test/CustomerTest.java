package com.wjl.test;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wjl.dao.CustomerDao;
import com.wjl.entity.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerTest {

	@Autowired
	private CustomerDao customerDao;

	@Test
	public void testSpec() {
		/**
		 * 自定义查询条件 1.实现spectification 接口 （提供泛型，查询的对象类型） 2.实现toPredicate方法（构造查询条件）
		 * 3.需要借助方法参数中的两个参数 root 获取需要查询的对象属性 CriteriaBuilder 构造查询条件 内部封装了很多查询条件（模糊匹配
		 * 精准匹配）
		 * 
		 * 案例： 根据客户查询 查询客户名称XX的客户 1.查询条件 cb 2.比较的属性名称 root对象
		 */
		Specification<Customer> spec = new Specification<Customer>() {

			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 1.获取比较的属性
				Path<Object> custName = root.get("custName");
				// 2.构造查询条件 select *from cst_customer where cust_name='XXX'
				// 参数1 需要比较的属性 （path对象）
				// 参数2 需要比较的取值
				Predicate predicate = cb.equal(custName, "李彦宏3");// 精准匹配 （比较的属性，比较的属性的取值）
				return predicate;
			}
		};
		Customer customer = customerDao.findOne(spec);
		System.out.println(customer);
	}

	/**
	 * 多条件查询 案例：根据客户名()和客户所属行业查询()
	 * 
	 * equal gt lt le like :得到path对象 根据path对象指定比较的参数类型 ，再进行比较
	 */

	@Test
	public void testSpec2() {
		Specification<Customer> spec = new Specification<Customer>() {

			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<Object> custName = root.get("custName");
				Path<Object> custIndustry = root.get("custIndustry");

				Predicate p1 = cb.like(custName.as(String.class), "李彦宏%");
				Predicate p2 = cb.equal(custIndustry, "百度");
				// 将多个查询条件组合到一起，组合(满足条件1并且满足条件2：与关系 满足1或满足2：或关系)
				Predicate and = cb.and(p1, p2);
				// Predicate p3 = cb.or(p1,p2);
				return and;
			}
		};
		List<Customer> list = customerDao.findAll(spec);
		for (Customer customer : list) {
			System.out.println(customer);
		}
	}

	/**
	 * 排序
	 */
	@Test
	public void testSort() {
		Specification<Customer> spec = new Specification<Customer>() {

			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<Object> custName = root.get("custName");
				Path<Object> custIndustry = root.get("custIndustry");

				Predicate p1 = cb.like(custName.as(String.class), "李彦宏%");
				Predicate p2 = cb.equal(custIndustry, "百度");
				// 将多个查询条件组合到一起，组合(满足条件1并且满足条件2：与关系 满足1或满足2：或关系)
				Predicate and = cb.and(p1, p2);
				// Predicate p3 = cb.or(p1,p2);
				return and;
			}
		};
		//第一个参数 排序的顺序（正序，倒序）
		//参数2 属性名
		Sort sort=new Sort(Sort.Direction.DESC,"custId");
		List<Customer> list = customerDao.findAll(spec,sort);
		for (Customer customer : list) {
			System.out.println(customer);
		}
	}
	
	/**
	 * 分页查询
	 * 1.findAll(Specification, Pageable);
	 *    Specification:查询条件
	 *    Pageable：查询的页码 每页查询的条数
	 * 2.findAll( Pageable);
	 * 返回 Page(springDataJpa封装好的pageBean对象，数据列表，总条数)
	 */
	
	@Test
	public void testSpec4()
	{
		//参数1  当前查询的页数（0开始）
		//参数2 每页查询的数量
		Pageable pageable=new PageRequest(0, 2);
		Page<Customer> page = customerDao.findAll(pageable);
		List<Customer> list = page.getContent();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		long totalElements = page.getTotalElements();//总条数
		System.out.println("总条数:"+totalElements);
		int totalPages = page.getTotalPages();//总页数
		System.out.println("总页数："+totalPages);
	}
}
