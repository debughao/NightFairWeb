package com.nightfair.dao.Interface;

import java.util.ArrayList;

import com.nightfair.vo.Coupon;
import com.nightfair.vo.SellerAndCoupon;

public interface ICouponDao {

	public abstract ArrayList<Coupon> getAllCouponBysellerId(int seller_id);
	public abstract Coupon addCoupon(Coupon coupon);
	public abstract Coupon updateCoupon(Coupon coupon);
	public abstract boolean deleteCouponByid(int id);
	public abstract ArrayList<SellerAndCoupon> getAllCoupon(String parm);
	public abstract ArrayList<Coupon> getAllGeuessCouponBysellerId(int seller_id);
	public abstract ArrayList<Coupon> getAllRecommandCouponBysellerId(int seller_id);

}
