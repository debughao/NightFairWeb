package com.nightfair.dao.Interface;

import java.util.ArrayList;

import com.nightfair.vo.Order;
import com.nightfair.vo.Buyer_order;


public interface IOrderDao {
	public abstract int insertOrder(Order Order);
	public abstract int selectNonPaymentOrderid(int user_id,int coupon_id);
	public abstract boolean updateOrder(int orderid,String way);
	public abstract boolean cancalOrder(int orderid);
	public abstract ArrayList<Buyer_order> selectOrders(int user_id,String state,String type);
	public abstract boolean createCouponcode(int orderid);	
}
