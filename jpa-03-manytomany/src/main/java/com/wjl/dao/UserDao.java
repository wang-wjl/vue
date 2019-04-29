package com.wjl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wjl.entity.User;

public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

}
