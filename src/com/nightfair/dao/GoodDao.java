package com.nightfair.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nightfair.dao.Interface.IGoodsDao;
import com.nightfair.uitl.DBUtils;
import com.nightfair.vo.Goods;

/**
 * 
 * 
 * GoodDao
 * 
 * 
 * 2015年9月11日 上午10:45:28
 * 
 * @version 1.0.0
 *
 */
public class GoodDao implements IGoodsDao {

	@Override
	public ArrayList<Goods> getGoodsBysellerid(int sellerid) {
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String sql = "select * from goods where seller_id= ?";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, sellerid);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String good_name = rs.getString("good_name");
				double real_price = rs.getDouble("real_price");
				int seller_counts = rs.getInt("seller_counts");
				String introduction = rs.getString("introduction");
				String img = rs.getString("img");
				int recommend_num = rs.getInt("recommend_num");
				goodsList
						.add(new Goods(id, good_name, real_price, seller_counts,
								introduction, img, sellerid, recommend_num));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.release(rs, preparedStatement, connection);
		}
		return goodsList;
	}

	@Override
	public Goods addGoods(Goods goods) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		System.out.println(goods);
		String sql = "insert into goods(seller_id,good_name,real_price,seller_counts,introduction) values(?,?,?,?,?)";	
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, goods.getSeller_id());
			preparedStatement.setString(2, goods.getGood_name());
			preparedStatement.setInt(3, goods.getSeller_counts());
			preparedStatement.setDouble(4, goods.getReal_price());
			preparedStatement.setString(5, goods.getIntroduction());
			preparedStatement.execute();
			rs = preparedStatement.getGeneratedKeys();
			while (rs.next()) {
				goods.setId(rs.getInt(1));
				System.out.println(goods.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(rs, preparedStatement, connection);
		return goods;
	}

	@Override
	public Goods getGoodsbyid(int id) {
		Goods goods = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql;
		sql = "select * from goods where id =? limit 1";
		ResultSet rs = null;

		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int seller_id = rs.getInt("seller_id");
				String good_name = rs.getString("good_name");
				double real_price = rs.getDouble("real_price");
				int seller_counts = rs.getInt("seller_counts");
				String introduction = rs.getString("introduction");
				String img = rs.getString("img");
				int recommend_num = rs.getInt("recommend_num");
				goods = new Goods(id, good_name, real_price, seller_counts,
						introduction, img, seller_id, recommend_num);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(rs, preparedStatement, connection);
		return goods;
	}

	@Override
	public boolean updateGoods(Goods goods) {
		boolean isSuccess = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "update goods set good_name=?,real_price=?,seller_counts=?,introduction=? where id =? limit 1 ";
		ResultSet rs = null;
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, goods.getGood_name());
			preparedStatement.setDouble(2, goods.getReal_price());
			preparedStatement.setInt(3, goods.getSeller_counts());
			preparedStatement.setString(4, goods.getIntroduction());
			preparedStatement.setInt(5, goods.getId());
			int m = preparedStatement.executeUpdate();
			if (m > 0) {
				isSuccess = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(rs, preparedStatement, connection);
		return isSuccess;
	}

	@Override
	public boolean deleteGoods(int id) {
		boolean isSuccess = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "delete from goods where id =? limit 1 ";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			int m = preparedStatement.executeUpdate();
			if (m > 0) {
				isSuccess = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(null, preparedStatement, connection);
		return isSuccess;
	}
	@Override
	public List<Goods> getGoods() {
		Goods goods = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<Goods> list=new ArrayList<Goods>();
		String sql;
		sql = "select * from goods limit 0,8";
		ResultSet rs = null;

		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String good_name = rs.getString("good_name");
				double real_price = rs.getDouble("real_price");
				int seller_counts = rs.getInt("seller_counts");
				String introduction = rs.getString("introduction");
				String img = rs.getString("img");
				int seller_id = rs.getInt("seller_id");
				int recommend_num = rs.getInt("recommend_num");
				goods = new Goods(id, good_name, real_price, seller_counts,
						introduction, img, seller_id, recommend_num);
				list.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(rs, preparedStatement, connection);
		return list;
	}

}
