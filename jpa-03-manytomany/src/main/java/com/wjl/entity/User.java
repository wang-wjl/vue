package com.wjl.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="sys_user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_age")
	private Integer userAge;
	
	/**
	 * 配置用户到角色的多对多关系
	 *   配置多对多的关系
	 *     1.声明表关系的配置
	 *         @ManyToMany  
	 *            targetEntity 代表对方的实体类字节码
	 *     2.配置中间表（包含两个外键）
	 *         @JoinTable
	 *            name  中间表的名称
	 *            joinColumns          当前对象在中间表中的外键
	 *            inverseJoinColumns   对方对象在中间表中的外键
	 */
	@ManyToMany(targetEntity=Role.class,cascade=CascadeType.ALL)
	@JoinTable(name="sys_user_role",//中间表
	//当前对象在中间表中的外键
	joinColumns= {@JoinColumn(name="sys_user_id",referencedColumnName="user_id")},
	//对方对象在中间表中的外键
	inverseJoinColumns= {@JoinColumn(name="sys_role_id",referencedColumnName="role_id")}) 
	private Set<Role> roles=new HashSet<Role>();
	
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	
	
}
