package com.shodom.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Document
public class WebUser {

	@Id
	private String id;
	private String userName;
	private String password;
	private List<GrantedAuthority> userRole;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<GrantedAuthority> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<GrantedAuthority> userRole) {
		this.userRole = userRole;
	}
}
