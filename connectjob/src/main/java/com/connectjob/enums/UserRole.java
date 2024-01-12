package com.connectjob.enums;

public enum UserRole {
	ADMIN("admin"),
	
	USUARIO("usuario"),
	
	EMPRESA("empresa");
	
	private String role;
	
	UserRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
	
}
