package com.sane.dh.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao extends SqlSessionDaoSupport {

	@Override 
	@Autowired
	 public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){  
         super.setSqlSessionFactory(sqlSessionFactory);  
     }  
	
}
