package com.nightfair.dao.Interface;

import java.util.ArrayList;
import com.nightfair.vo.Goods;

public interface IGoodsDao {
	public abstract ArrayList<Goods> getGoodsBysellerid(int seller_id);
	public abstract Goods addGoods(Goods good);
	public abstract Goods getGoodsbyid(int id);
	public abstract boolean updateGoods(Goods goods);
	public abstract boolean deleteGoods(int id);
}
