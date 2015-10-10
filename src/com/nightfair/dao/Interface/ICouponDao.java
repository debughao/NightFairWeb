package com.nightfair.dao.Interface;

import java.util.ArrayList;

import com.nightfair.vo.Coupon;

public interface ICouponDao {

	public abstract ArrayList<Coupon> getAllCouponBysellerId(int seller_id);

	public abstract Coupon addCoupon(Coupon coupon);
	public abstract Coupon updateCoupon(Coupon coupon);
	public abstract boolean deleteCouponByid(int id);
}
