package com.nightfair.dao.Interface;

import com.nightfair.vo.Order;


public interface IOrderDao {
	public abstract int insertOrder(Order Order);
	public abstract int selectNonPaymentOrderid(int user_id,int coupon_id);
	public abstract boolean updateOrder(int orderid,String way);
}
