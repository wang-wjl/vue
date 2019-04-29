package com.wjl.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.wjl.entity.Customer;

/**
 * 1.需要继承两个接口 （JpaRepository,JpaSpecificationExecutor）
 *     JpaRepository<操作的实体类类型，实体类中主键属性的类型>
 *       *封装了基本的CRUD操作
 *     JpaSpecificationExecutor<操作的实体类类型>
 *       *封装了复杂查询（分页等）
 * 2.需要提供响应的泛型
 * 
 * 
 * 1.通过JDKDynamicAopProxy创建动态代理对象
 * 2.动态代理对象 simpleJpaRepository（实现了 JpaRepository，JpaSpecificationExecutor接口）
 * 3.findOne() 通过entityManager完成查询操作
 * 
 * 
 * @JPQL的查询方式
 *    需要将JPQL已经配置到接口方法上
 *    1.特有的查询：需要在dao接口上配置方法
 *    2.在新添加的方法上，使用注解的形式配置jpql查询语句
 *    3.注解：@Query
 * @author wjl
 *
 */
public interface CustomerDao extends JpaRepository<Customer,Long> ,JpaSpecificationExecutor<Customer>
{
	/**
	 * jpql:from Customer where custName= ?
	 * @param custName
	 * @return
	 */
	@Query(value="from Customer where custName= ?")
	public Customer findJpql(String custName);
	
	/**
	 * 根据客户名称和id查询
	 * 多个占位符 赋值的时候，默认的情况下  占位符的位置需要和方法参数中位置一致
	 * 
	 * 指定占位符参数的位置
	 *   ? 索引的方式 指定此占位的取值来源
	 * jpql：from Customer where custId=? and custName= ?
	 */
	@Query(value="from Customer where custId=?2 and custName= ?1")
	public Customer findCustNameId(String name,Long id);
	
	
	/**
	 * 使用jpql完成更新
	 * @Query 代表进行查询
	 *    * 声明此方法是用来更新操作
	 * @Modifying
	 *   当前执行的是一个更新操作
	 * SQL : update cst_customer set cust_name= ? where cust_id = ?
	 * jpql: update Customer set custName=? where custId=?
	 */
	@Query(value="update Customer set custName=?2 where custId=?1")
	@Modifying
	public void updateById(Long custId,String custName);
	
	
	/**iii sql语句的查询
	 * 1.特有的查询 需要在dao接口上配置方法
	 * 2.在新添加的方法上，使用注解的形式配置sql查询语句
	 * 3注解 @Query
	 *   value: jpql 语句 | sql语句
	 *   nativeQuery:false (使用jpsql查询) | true (使用本地查询：sql查询)
	 */
	
	/**
	 * 使用sql的形式查询
	 *   查询全部的客户
	 *   sql :select *from cst_customer;
	 */
	@Query(value="select *from cst_customer",nativeQuery=true)
	public List<Object[]> findSqlAll(); 
	
	/**
	 * sql 查询
	 */
	@Query(value="select *from cst_customer where cust_name like ?1",nativeQuery=true)
	public List<Object[]> findSqlByName(String name);
	
	/**
	 * iiii 方法名称规则查询
	 *  * 对jpql查询 ，更加深入的一层封装，我们只需要安装springDataJpa提供的方法名称规则定义方法，
	 *    不需要再去配置jpql语句，完成查询
	 */
	/**
	 * 方法名的约定
	 *   1.findBy:查询
	 *       对象中的属性名称（首字母大写），查询的条件
	 *       *默认情况，使用等于的方式查询
	 *           特殊的查询方式
	 *           
	 * findByCustomer 根据客户名称查询
	 * 
	 * 再springdataJpa的运行阶段
	 *    会根据方法名称进行解析，findBy from xxx(实体类)
	 *                                    属性名称  where custName=
	 * 1.findBy +属性名称（根据属性名称进行完成匹配的查询）
	 * 2.findBy + 属性名称 + "查询方式（Like | isnull ）"
	 *         findByCustNameLike
	 * 3.多条件查询
	 *      findBy + 属性 + "查询方式" + "多条件的连接符( and | or )" + 属性 + "(查询方式)"
	 */
	public Customer findByCustName(String custName);
	public List<Customer> findByCustNameLike(String custName);
	
	//使用客户名称模糊匹配 和客户所属行业精准匹配查询
	public List<Customer> findByCustNameLikeAndCustIndustry(String custName,String custIndustru);
	
	
	
	
}
