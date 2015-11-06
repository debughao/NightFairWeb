package com.nightfair.dao.Interface;

import com.nightfair.vo.Recharge;

public interface IAccountDao {
	public abstract boolean insertAccount(int seller_id);
	public abstract boolean insertRecharge(Recharge recharge);
	public abstract double  selectAccount(int user_id);
	/**
	 * 充值
	 * @param user_id
	 * @return
	 */
	public abstract boolean  updateAccountbalance(int user_id,String opeartion,double money);
	/*
	 * 修改支付密码
	 */
	public abstract boolean  updatPayword(int user_id,String payword);
}
