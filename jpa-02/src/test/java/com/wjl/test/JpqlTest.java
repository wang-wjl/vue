package com.wjl.test;


import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wjl.dao.CustomerDao;
import com.wjl.entity.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class JpqlTest {
	@Autowired
	private CustomerDao customerDao;
	
    @Test
    public void testFindJpql()
    {
    	Customer customer = customerDao.findJpql("李彦宏");
    	System.out.println(customer);
    }
    
    @Test
    public void testfindCustNameId()
    {
    	Customer customer = customerDao.findCustNameId( "李彦宏",5l);
    	System.out.println(customer);
    }
    /**
     * 更新操作
     * jpql 完成更新 删除 必须加事务
     *    *需要手动添加事务的支持
     *    *默认会执行结束后 回滚事务 
     */
    @Test
    @Transactional  //
    @Rollback(value=false)
    public void testUpdateById()
    {
        customerDao.updateById(5l, "李彦宏3");
    	System.out.println(customerDao.findOne(5l));
    }
    @Test
    public void testFindSql()
    {
        List<Object[]> list = customerDao.findSqlAll();
        for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}
    }
    
    @Test
    public void testfindSqlByName()
    {
    	 List<Object[]> list = customerDao.findSqlByName("李%");
    	 for (Object[] objects : list) {
 			System.out.println(Arrays.toString(objects));
 		}
    }
    
    /**
     * 方法命名规则查询方式
     */
    @Test
    public void testNaming()
    {
    	Customer customer = customerDao.findByCustName("李彦宏3");
    	System.out.println(customer);
    }
    
    @Test
    public void testfindByCustNameLike()
    {
    	List<Customer> list = customerDao.findByCustNameLike("李%");
    	for (Customer customer : list) {
			System.out.println(customer);
		}
    }
    
    @Test
    public void testfindByCustNameLikeAndCustIndustry()
    {
    	List<Customer> list = customerDao.findByCustNameLikeAndCustIndustry("李%","百度");
    	for (Customer customer : list) {
			System.out.println(customer);
		}
    }
    

}
