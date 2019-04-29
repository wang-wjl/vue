package com.wjl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wjl.entity.Customer;

public interface CustomerDao  extends JpaRepository<Customer, Long> ,JpaSpecificationExecutor<Customer>{

}
