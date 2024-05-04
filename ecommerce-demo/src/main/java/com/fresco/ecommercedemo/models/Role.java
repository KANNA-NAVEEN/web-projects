package com.fresco.ecommercedemo.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role {
	CONSUMER,
	SELLER;
	
}

class RoleGrantedAuthority implements GrantedAuthority {
	private static final long serialVersionUID = 9141785947162729136L;
	String role;

	public RoleGrantedAuthority(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return this.role;
	}

}