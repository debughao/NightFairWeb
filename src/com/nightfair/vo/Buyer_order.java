package com.nightfair.vo;

public class Buyer_order {
	private SellerInfo sellerInfo;
	private Order order;
	private Coupon coupon;

	public SellerInfo getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(SellerInfo sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public Buyer_order(SellerInfo sellerInfo, Order order, Coupon coupon) {
		super();
		this.sellerInfo = sellerInfo;
		this.order = order;
		this.coupon = coupon;
	}

	public Buyer_order() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "buyer_dao [sellerInfo=" + sellerInfo + ", order=" + order + ", coupon=" + coupon + "]";
	}

}
