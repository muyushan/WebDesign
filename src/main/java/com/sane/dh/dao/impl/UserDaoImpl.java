package com.sane.dh.dao.impl;

import org.springframework.stereotype.Repository;

import com.sane.dh.dao.UserDao;
import com.sane.dh.model.user.UserRegistInfo;
@Repository(value = "userDao")
public class UserDaoImpl extends BaseDao implements UserDao {
private String userDaoNameSpace="com.sane.dh.dao.UserDao.";
	@Override
	public boolean addUser(UserRegistInfo userRegistInfo) {
		int insertCount=getSqlSession().insert(userDaoNameSpace+"addUser", userRegistInfo);
		return insertCount>0?true:false;
	}
	@Override
	public UserRegistInfo getUserByEmail_phone(String email_phone) {
		UserRegistInfo userRegistInfo=getSqlSession().selectOne(userDaoNameSpace+"getUserByEmail_phone", email_phone);
		return userRegistInfo;
	}

}
