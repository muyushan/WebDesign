package com.sane.dh.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sane.dh.dao.UserDao;
import com.sane.dh.model.user.UserRegistInfo;
import com.sane.dh.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Override
	public Boolean saveNewUser(UserRegistInfo userRegistInfo) {
		return  userDao.addUser(userRegistInfo);
	}
	@Override
	public UserRegistInfo getUserInfo(String email_phone) {
		// TODO Auto-generated method stub
		return userDao.getUserByEmail_phone(email_phone);
	}

}
