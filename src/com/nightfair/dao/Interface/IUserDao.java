package com.nightfair.dao.Interface;

import com.nightfair.vo.SellerInfo;
import com.nightfair.vo.User;

public interface IUserDao {
	public abstract SellerInfo getUserInfoByUser_id(int User_id);

	public abstract boolean existUserByUsername(String parameter);

	public abstract boolean existUserByPhone(String parameter);

	public abstract boolean existUserByEmail(String parameter);

	public abstract boolean regiserUser(User uer);
	public abstract User isPassLogin(String username,String password,String type);
}
