package com.wjl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * 联系人--dao
 * @author wjl
 *
 */
public interface LinkManDao extends JpaRepository<LinkManDao, Long>, JpaSpecificationExecutor<LinkManDao> {

}
