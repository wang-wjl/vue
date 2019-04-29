package com.wjl.entity;

import java.util.HashSet;
import java.util.Set;

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
@Table(name="sys_role")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private Long roleId;
	@Column(name="role_name")
	private String roleName;
	
	/**
	 * 配置多对多
	 */
//	@ManyToMany(targetEntity=User.class)
//	@JoinTable(name="sys_user_role",//中间表
//	//当前对象在中间表中的外键
//	joinColumns= {@JoinColumn(name="sys_role_id",referencedColumnName="role_id")},
//	//对方对象在中间表中的外键
//	inverseJoinColumns= {@JoinColumn(name="sys_user_id",referencedColumnName="user_id")}) 
	
	@ManyToMany(mappedBy="roles")
	private Set<User> users=new HashSet<User>();
	
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
	
	
}
