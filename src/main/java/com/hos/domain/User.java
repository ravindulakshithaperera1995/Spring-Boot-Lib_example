package com.hos.domain;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="user")
public class User {
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String pwd;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public User() {
		super();
	}
	
	

}
