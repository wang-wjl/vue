package com.wjl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wjl.entity.Customer;
import com.wjl.entity.LinkMan;

public interface LinkManDao  extends JpaRepository<LinkMan, Long> ,JpaSpecificationExecutor<LinkMan>{

}
