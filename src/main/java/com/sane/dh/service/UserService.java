package com.sane.dh.service;

import com.sane.dh.model.user.UserRegistInfo;

public interface UserService {
 
	public Boolean saveNewUser(UserRegistInfo userRegistInfo);
	public UserRegistInfo getUserInfo(String email_phone);
	public boolean phone_emailIsRegisted(String email_phone); 
}
