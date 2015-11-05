package com.nightfair.dao.Interface;

public interface IAccountDao {
	public abstract boolean insertAccount(int seller_id);
	public abstract int  selectAccount(int user_id);
	/**
	 * 充值
	 * @param user_id
	 * @return
	 */
	public abstract boolean  updateAccountbalance(int user_id,String opeartion,int money);
	/*
	 * 修改支付密码
	 */
	public abstract boolean  updatPayword(int user_id,String payword);
}
