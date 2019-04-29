package com.wjl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wjl.domain.User;

/**
 * 用户的持久层接口
 * @author Administrator
 *
 */
public interface IUserDao {
	/**
	 * 查询用户列表
	 */@Select("select *from user ")
	List<User> findAll();
	/**
	 * 查找用户
	 * @param userId
	 * @return
	 */
	 @Select("select *from user where id= #{userId}")
	User findById(Integer userId);
	/**
	 * 更新用户
	 * @param user
	 */
	 @Update("update user set username=#{username},password=#{password},age=#{age},sex=#{sex},email=#{email} where id=#{id}")
	void updateUser(User user);
}
