package com.nightfair.dao.Interface;

import com.nightfair.vo.SellerInfo;
import com.nightfair.vo.User;

public interface IUserDao {
	public abstract SellerInfo getUserInfoByUser_id(int User_id);

	public abstract boolean existUserByUsername(String parameter,String type);

	public abstract boolean existUserByPhone(String parameter,String type);

	public abstract boolean existUserByEmail(String parameter,String type);

	public abstract int  phoneRegiserUser(User uer);
	public abstract int  regiserUser(User uer);
	public abstract User isPassLogin(String username,String password,String type);
	
}
