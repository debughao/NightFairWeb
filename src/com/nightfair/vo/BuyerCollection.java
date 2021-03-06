package com.nightfair.vo;

public class BuyerCollection {
	private SellerInfo sellerInfo;
	private Collection collection;
	private Coupon coupon;
	public BuyerCollection() {
		// TODO Auto-generated constructor stub
	}
	public SellerInfo getSellerInfo() {
		return sellerInfo;
	}
	public void setSellerInfo(SellerInfo sellerInfo) {
		this.sellerInfo = sellerInfo;
	}
	public Collection getCollection() {
		return collection;
	}
	public void setCollection(Collection collection) {
		this.collection = collection;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public BuyerCollection(SellerInfo sellerInfo, Collection collection, Coupon coupon) {
		super();
		this.sellerInfo = sellerInfo;
		this.collection = collection;
		this.coupon = coupon;
	}
	@Override
	public String toString() {
		return "BuyerCollection [sellerInfo=" + sellerInfo + ", collection=" + collection + ", coupon=" + coupon + "]";
	}

}
