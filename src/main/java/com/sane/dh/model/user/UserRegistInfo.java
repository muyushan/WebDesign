package com.sane.dh.model.user;

import org.springframework.stereotype.Component;

@Component
public class UserRegistInfo {
 private  String email_phone;
 private  String password; 
 private  String confirm_password;
 private  UserIdType userIdType;
 private  int statusCode;
 private  String VerificationCode;
public int getStatusCode() {
	return statusCode;
}
public void setStatusCode(int statusCode) {
	this.statusCode = statusCode;
}
public String getVerificationCode() {
	return VerificationCode;
}
public void setVerificationCode(String verificationCode) {
	VerificationCode = verificationCode;
}
public UserIdType getUserIdType() {
	return userIdType;
}
public void setUserIdType(String userIdType) {
	this.userIdType = UserIdType.valueOf(userIdType);
}
public String getEmail_phone() {
	return email_phone;
}
public void setEmail_phone(String email_phone) {
	if (email_phone.contains("@")) {
		userIdType=UserIdType.EMAIL;
	}else{
		userIdType=UserIdType.PHONE;
	}
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
