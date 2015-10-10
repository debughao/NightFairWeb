package com.nightfair.dao.Interface;

import com.nightfair.vo.SellerInfo;


public interface ISellerDao {
public abstract int getSelleridByUid(int User_id);
public abstract boolean updateSellerInfo(SellerInfo sellerInfo);
public abstract SellerInfo getSellerInfoByUser_id(int User_id);
public abstract boolean existSeller(String id);
public abstract boolean uploadshopimage(String imageur, int userid);
}
