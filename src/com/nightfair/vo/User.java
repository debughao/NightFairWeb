package com.nightfair.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: User
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author debughao
 * @date 2015年9月16日
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private int u_id;
	private String username;
	private String email;
	private String phone;
	private String password;
	private String  type;

	public User() {
		super();
	}

	public User(int u_id,  String email, String phone, String type) {
		super();
		this.u_id = u_id;
		this.email = email;
		this.phone = phone;
		this.type = type;
	}

	public User(int u_id,  String username, String email, String phone, String password, String type) {
		super();
		this.u_id = u_id;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.type = type;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", username=" + username + ",  email=" + email
				+ ", phone=" + phone + ", password=" + password + ", type=" + type + "]";
	}



}
