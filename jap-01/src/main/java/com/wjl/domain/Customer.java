package com.wjl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 客户的实体类
 *    配置映射关系
 *       1.实体类和表的映射关系
 *       2.实体类中属性和表中字段的映射关系
 * @author wjl
 * @Entity  声明实体类
 * @Table   配置实体类和表的映射关系
 *    name  表的名称
 * 
 * 2.实体类中属性和表中字段的映射关系
 */
@Entity  //声明实体类
@Table(name="cst_customer")    
public class Customer {
	/**
	 * @Id 声明主键的配置
	 * @GeneratedValue  配置主键的生成策略
	 *    GenerationType.IDENTITY  ,自增( mysql)
	 *      *  底层数据库必须支持自动增长（对id）
	 *    GenerationType.SEQUENCE ，序列
	 *         oracle
	 *    GenerationType.TABLE  通过一张数据库表的形式完成主键自增
	 *    GenerationType.AUTO   程序自动帮助我们创建主键生成策略
	 * @Column  配置属性和字段的映射关系
	 *    name 数据库表中字段的名称
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cust_id")
	private Long custId;  //主键
	@Column(name="cust_name")
	private String custName;
	
	@Column(name="cust_source")
	private String custSource;
	
	@Column(name="cust_level")
	private String custLevel;
	
	@Column(name="cust_industry")
	private String custIndustry;
	
	@Column(name="cust_phone")
	private String custPhone;
	
	@Column(name="cust_address")
	private String custAddress;
	
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustSource() {
		return custSource;
	}
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public String getCustIndustry() {
		return custIndustry;
	}
	public void setCustIndustry(String custIndustry) {
		this.custIndustry = custIndustry;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", custSource=" + custSource + ", custLevel="
				+ custLevel + ", custIndustry=" + custIndustry + ", custPhone=" + custPhone + ", custAddress="
				+ custAddress + "]";
	}
}
