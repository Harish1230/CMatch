package com.cgi.UserRegistration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class User {
	
	@Id
//	@Column(name="user_id",length=30)
	private String userId;
//	@Column(name="userName")
	private String userName;
//	@Column(name="user_password")
	private String userPassword;
//	@Column(name="mobile_number")
	private String mobilenumber;
	
	public User(String userId, String userName, String userPassword, String mobilenumber) {
		super();
		this.userId = userId;
		this.userName=userName;
		this.userPassword = userPassword;
		this.mobilenumber = mobilenumber;
	}
	
	public User() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", mobilenumber=" + mobilenumber + "]";
	}

}
