package com.nightfair.vo;

import java.io.Serializable;

public class BuyerInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String user_id;
	private String nickname;
	private String sex;
	private String age;
	private String star;
	private String hometown;
	private String address;
	private String autograph;
	private String image;

	public BuyerInfo() {

	}


	@Override
	public String toString() {
		return "BuyerInfo [id=" + id + ", user_id=" + user_id + ", nickname=" + nickname + ", sex=" + sex + ", age="
				+ age + ", star=" + star + ", hometown=" + hometown + ", address=" + address + ", autograph="
				+ autograph + ", image=" + image + "]";
	}


	public BuyerInfo(String user_id, String nickname) {
		super();
		this.user_id = user_id;
		this.nickname = nickname;
	}

	public BuyerInfo(int id, String user_id, String nickname, String sex, String age, String star, String hometown,
			String address, String autograph, String image) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.nickname = nickname;
		this.sex = sex;
		this.age = age;
		this.star = star;
		this.hometown = hometown;
		this.address = address;
		this.autograph = autograph;
		this.image = image;
	}


	public BuyerInfo(String user_id, String nickname, String sex, String age, String star, String hometown,
			String address, String autograph, String image) {
		super();
		this.user_id = user_id;
		this.nickname = nickname;
		this.sex = sex;
		this.age = age;
		this.star = star;
		this.hometown = hometown;
		this.address = address;
		this.autograph = autograph;
		this.image = image;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAutograph() {
		return autograph;
	}

	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
