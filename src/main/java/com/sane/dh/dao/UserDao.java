package com.sane.dh.dao;

import com.sane.dh.model.user.UserRegistInfo;

public interface UserDao {
 
	public boolean addUser(UserRegistInfo userRegistInfo);
	public UserRegistInfo getUserByEmail_phone(String email_phone);
	public boolean email_phoneIsRegisted(String email_phone);
}
