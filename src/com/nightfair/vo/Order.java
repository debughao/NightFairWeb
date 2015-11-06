package com.nightfair.vo;

public class Order {
	private int order_id;
	private int seller_id;
	private int user_id;
	private int coupon_id;
	private String order_time;
	private String num;
	private double amount;
	private int state;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getOrder_time() {
		return order_time;
	}

	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Order(int order_id, int seller_id, int user_id, int coupon_id, String order_time, String num, double amount,
			int state) {
		super();
		this.order_id = order_id;
		this.seller_id = seller_id;
		this.user_id = user_id;
		this.coupon_id = coupon_id;
		this.order_time = order_time;
		this.num = num;
		this.amount = amount;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", seller_id=" + seller_id + ", user_id=" + user_id + ", coupon_id="
				+ coupon_id + ", order_time=" + order_time + ", num=" + num + ", amount=" + amount + ", state=" + state
				+ "]";
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

}
