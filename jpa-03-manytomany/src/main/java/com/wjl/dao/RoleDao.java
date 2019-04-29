package com.wjl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wjl.entity.Role;

public interface RoleDao extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

}
