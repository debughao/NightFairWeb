package com.nightfair.vo;



public class Coupon {
	private int id;
	private int seller_id;
	private int original_price;
	private int current_price;
	private String description;
	private String public_time;
	private String update_time;
	private int  seller_counts;
	private int state;
	private int isguess;
	private int isrecommend;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	public int getOriginal_price() {
		return original_price;
	}
	public void setOriginal_price(int original_price) {
		this.original_price = original_price;
	}
	public int getCurrent_price() {
		return current_price;
	}
	public void setCurrent_price(int current_price) {
		this.current_price = current_price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPublic_time() {
		return public_time;
	}
	public void setPublic_time(String public_time) {
		this.public_time = public_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public int getSeller_counts() {
		return seller_counts;
	}
	public void setSeller_counts(int seller_counts) {
		this.seller_counts = seller_counts;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getIsguess() {
		return isguess;
	}
	public void setIsguess(int isguess) {
		this.isguess = isguess;
	}
	public int getIsrecommend() {
		return isrecommend;
	}
	public void setIsrecommend(int isrecommend) {
		this.isrecommend = isrecommend;
	}
	public Coupon() {
	
	}
	/**
	 * 
	 * 创建一个新的实例 Coupon. 用于接口使用 
	 * 
	 * @param id
	 * @param seller_id
	 * @param original_price
	 * @param current_price
	 * @param description
	 * @param public_time
	 * @param update_time
	 * @param seller_counts
	 * @param state
	 */
	public Coupon(int id, int seller_id, int original_price, int current_price,
			String description, String public_time, String update_time,
			int seller_counts, int state) {
		super();
		this.id = id;
		this.seller_id = seller_id;
		this.original_price = original_price;
		this.current_price = current_price;
		this.description = description;
		this.public_time = public_time;
		this.update_time = update_time;
		this.seller_counts = seller_counts;
		this.state = state;
	}
	/**
	 * 
	 * 创建一个新的实例 Coupon. 用于商家添加优惠券 
	 * 
	 * @param seller_id
	 * @param original_price
	 * @param current_price
	 * @param description
	 * @param public_time
	 * @param update_time
	 * @param seller_counts
	 * @param state
	 */
	public Coupon(int seller_id, int original_price, int current_price,
			String description, String public_time, String update_time,
			int seller_counts, int state) {
		super();
		this.seller_id = seller_id;
		this.original_price = original_price;
		this.current_price = current_price;
		this.description = description;
		this.public_time = public_time;
		this.update_time = update_time;
		this.seller_counts = seller_counts;
		this.state = state;
	}


	public Coupon(int id, int seller_id, int original_price, int current_price, String description, String public_time,
			String update_time, int seller_counts, int state, int isguess, int isrecommend) {
		super();
		this.id = id;
		this.seller_id = seller_id;
		this.original_price = original_price;
		this.current_price = current_price;
		this.description = description;
		this.public_time = public_time;
		this.update_time = update_time;
		this.seller_counts = seller_counts;
		this.state = state;
		this.isguess = isguess;
		this.isrecommend = isrecommend;
	}
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", seller_id=" + seller_id + ", original_price=" + original_price
				+ ", current_price=" + current_price + ", description=" + description + ", public_time=" + public_time
				+ ", update_time=" + update_time + ", seller_counts=" + seller_counts + ", state=" + state
				+ ", isguess=" + isguess + ", isrecommend=" + isrecommend + "]";
	}
	
}
