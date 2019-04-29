package com.wjl.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * 1.实体类和表的映射关系
 *   @Entity
 *   @Table
 * 2.类中属性和表中字段的映射关系
 *   @Id
 *   @GeneratedValue
 *   @Column
 * @author wjl
 *
 */
@Entity
@Table(name="cst_customer")
public class Customer {
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
	
	/**
	 * 配置客户和联系人之间的关系（）一对多
	 * 使用注解的心事配置多表关系
	 *   1.声明关系
	 *       @OneToMany 配置一对多关系
	 *           targetENtity  对方对象的字节码对象
	 *   2.配置外键（中间表）
	 *       @JoinColumn 配置外键
	 *           name：外键字段的名称
	 *           referencedColumnName：参照的主表的主键字段名称
	 * 在客户实体类上（一的一方）添加了外键了配置，所以对客户而言，也具备了维护外键的作用
	 */
//	@OneToMany(targetEntity=LinkMan.class)
//	@JoinColumn(name="lkm_cust_id",referencedColumnName="cust_id")
	/**
	 * 放弃外键维护权
	 *   mappedBy:对方配置关系的属性名称
	 *   cascade：配置级联 （可以配置到设置多表的映射关系的注解上）
	 *       CascadeType.ALL     所有
	 *       CascadeType.MERGE   更新
	 *       CascadeType.PERSIST 保存
	 *       CascadeType.REMOVE  删除
	 *   fetch 配置关联对象的加载方式
	 *      fetch=FetchType.EAGER 立即加载
	 *      FetchType.LAZY        延时加载
	 */
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
	private Set<LinkMan> linkMans=new HashSet<LinkMan>();
	
	public Set<LinkMan> getLinkMans() {
		return linkMans;
	}

	public void setLinkMans(Set<LinkMan> linkMans) {
		this.linkMans = linkMans;
	}

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
