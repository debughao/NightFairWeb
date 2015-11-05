package com.nightfair.vo;

import java.util.ArrayList;

public class SellerAndCoupon {
	private int id;
	private int user_id;
	private String seller_name;
	private String phone;
	private String province;
	private String city;
	private String arer;
	private String street;
	private String latitude;
	private String longitude;
	private double  rank;
	private String classify_id;
	private String img;
	private ArrayList<Coupon> coupons;	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArer() {
		return arer;
	}

	public void setArer(String arer) {
		this.arer = arer;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public double getRank() {
		return rank;
	}

	public void setRank(double rank) {
		this.rank = rank;
	}

	public String getClassify_id() {
		return classify_id;
	}

	public void setClassify_id(String classify_id) {
		this.classify_id = classify_id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public ArrayList<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}

	public SellerAndCoupon(int id, int user_id, String seller_name, String phone, String province, String city,
			String arer, String street, String latitude, String longitude, double rank, String classify_id, String img,
			ArrayList<Coupon> coupons) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.seller_name = seller_name;
		this.phone = phone;
		this.province = province;
		this.city = city;
		this.arer = arer;
		this.street = street;
		this.latitude = latitude;
		this.longitude = longitude;
		this.rank = rank;
		this.classify_id = classify_id;
		this.img = img;
		this.coupons = coupons;
	}

	public SellerAndCoupon() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SellerAndCoupon [id=" + id + ", user_id=" + user_id + ", seller_name=" + seller_name + ", phone="
				+ phone + ", province=" + province + ", city=" + city + ", arer=" + arer + ", street=" + street
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", rank=" + rank + ", classify_id="
				+ classify_id + ", img=" + img + ", coupons=" + coupons + "]";
	}
	
}
