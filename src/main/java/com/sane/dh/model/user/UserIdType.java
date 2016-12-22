package com.sane.dh.model.user;

public enum UserIdType {
	
	EMAIL("email"),PHONE("phone");
	private UserIdType(String type){
		this.type=type;
	}
	private  String type;
}
