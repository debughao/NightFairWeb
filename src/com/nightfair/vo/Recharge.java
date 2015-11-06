package com.nightfair.vo;

public class Recharge {
	private int user_id;
	private String	transactionid;
	private String recharge_way;
	private double amount;
	private String datetime;
	
	public Recharge(int user_id, String transactionid, String recharge_way, double amount, String datetime) {
		super();
		this.user_id = user_id;
		this.transactionid = transactionid;
		this.recharge_way = recharge_way;
		this.amount = amount;
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "Recharge [user_id=" + user_id + ", transactionid=" + transactionid + ", recharge_way=" + recharge_way
				+ ", amount=" + amount + ", datetime=" + datetime + "]";
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public String getRecharge_way() {
		return recharge_way;
	}

	public void setRecharge_way(String recharge_way) {
		this.recharge_way = recharge_way;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public Recharge() {
		// TODO Auto-generated constructor stub
	}

}
