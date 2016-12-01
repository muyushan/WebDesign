package com.sane.dh.model.user;

import org.springframework.stereotype.Component;

@Component
public class UserRegistInfo {
 private  String email_phone;
 private  String password;
 private  String confirm_password;
public String getEmail_phone() {
	return email_phone;
}
public void setEmail_phone(String email_phone) {
	this.email_phone = email_phone;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getConfirm_password() {
	return confirm_password;
}
public void setConfirm_password(String confirm_password) {
	this.confirm_password = confirm_password;
}

 
 
}
