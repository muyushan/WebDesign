package com.sane.dh.service;

import com.sane.dh.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
 
	public Boolean saveNewUser(User userRegistInfo);
	public User getUserInfo(String userName);
	public boolean userNameIsRegisted(String userName);
}
