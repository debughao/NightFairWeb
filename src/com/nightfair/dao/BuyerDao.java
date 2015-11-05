package com.nightfair.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nightfair.dao.Interface.IBuyerDao;
import com.nightfair.uitl.DBUtils;
import com.nightfair.vo.BuyerInfo;

public class BuyerDao implements IBuyerDao {

	@Override
	public BuyerInfo getBuyerinfo(int user_id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BuyerInfo buyerInfo = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM buyer_info WHERE user_id=? LIMIT 1";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {					
				String nickname = resultSet.getString("nickname");
				String sex = resultSet.getString("sex");
				String age = resultSet.getString("age");
				String star = resultSet.getString("star");
				String hometown = resultSet.getString("hometown");
				String address = resultSet.getString("address");
				String autograph = resultSet.getString("autograph");
				String image = resultSet.getString("image");
				buyerInfo = new BuyerInfo(String.valueOf(user_id), nickname, sex, age, star, hometown, address, autograph, image);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(resultSet, preparedStatement, connection);
		return buyerInfo;
		
	}
	@Override
	public boolean updateBuyerHd(String image_url, int userid) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean isSuccess = false;
		String sql = "update buyer_info set image=? where user_id=? limit 1";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, image_url);
			preparedStatement.setInt(2, userid);
			int m = preparedStatement.executeUpdate();
			if (m > 0) {
				isSuccess = true;
				System.out.println("保存到数据库成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtils.release(resultSet, preparedStatement, connection);
		return isSuccess;

	}

	@Override
	public boolean updateBuyerInfo(BuyerInfo buyerInfo) {
		boolean isSuccess = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		System.out.println(buyerInfo);
		String sql = "update buyer_info set nickname=?,sex=?,age=?, star=?, hometown=?,"
				+ " address=?,autograph=? where user_id=? ";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, buyerInfo.getNickname());
			preparedStatement.setString(2, buyerInfo.getSex());
			preparedStatement.setString(3, buyerInfo.getAge());
			preparedStatement.setString(4, buyerInfo.getStar());
			preparedStatement.setString(5, buyerInfo.getHometown());
			preparedStatement.setString(6, buyerInfo.getAddress());
			preparedStatement.setString(7, buyerInfo.getAutograph());
			preparedStatement.setString(8, buyerInfo.getUser_id());
			int m = preparedStatement.executeUpdate();
			if (m > 0) {
				isSuccess = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBUtils.release(resultSet, preparedStatement, connection);
		}
		return isSuccess;
	}
	@Override
	public boolean insertBuyerInfo(BuyerInfo buyerInfo) {
		// TODO Auto-generated method stub
		boolean f = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "insert into buyer_info(user_id,nickname) values(?,?)";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);		
			preparedStatement.setString(1, buyerInfo.getUser_id());
			preparedStatement.setString(2,  buyerInfo.getNickname());
			int n=preparedStatement.executeUpdate();
			if (n>0) {
				f=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.release(null, preparedStatement, connection);
			System.out.println(f);
		}
		return f;
	}
	@Override
	public BuyerInfo getBuyerInfoByphone(String phone) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BuyerInfo buyerInfo = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM user, buyer_info WHERE user.u_id=buyer_info.user_id AND phone=? LIMIT 1";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, phone);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {	
				String user_id= resultSet.getString("user_id");
				String nickname = resultSet.getString("nickname");
				String sex = resultSet.getString("sex");
				String age = resultSet.getString("age");
				String star = resultSet.getString("star");
				String hometown = resultSet.getString("hometown");
				String address = resultSet.getString("address");
				String autograph = resultSet.getString("autograph");
				String image = resultSet.getString("image");
				buyerInfo = new BuyerInfo(user_id, nickname, sex, age, star, hometown, address, autograph, image);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(resultSet, preparedStatement, connection);
		return buyerInfo;
		
	}


}
