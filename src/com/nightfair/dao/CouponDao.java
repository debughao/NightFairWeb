package com.nightfair.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.nightfair.dao.Interface.ICouponDao;
import com.nightfair.uitl.DBUtils;
import com.nightfair.vo.Coupon;

public class CouponDao implements ICouponDao {

	@Override
	public ArrayList<Coupon> getAllCouponBysellerId(int seller_id) {
		ArrayList<Coupon> couponsList = new ArrayList<Coupon>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String sql = "select * from coupon where seller_id= ?";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, seller_id);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int original_price = rs.getInt("original_price");
				int current_price = rs.getInt("current_price");
				String description = rs.getString("description");
				String public_time = rs.getString("public_time");
				String update_time = rs.getString("update_time");
				int seller_counts = rs.getInt("seller_counts");
				int state = rs.getInt("state");
				Coupon coupon = new Coupon(id, seller_id, original_price,
						current_price, description, public_time, update_time,
						seller_counts, state);
				couponsList.add(coupon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.release(rs, preparedStatement, connection);
		}
		return couponsList;
	}

	@Override
	public Coupon addCoupon(Coupon coupon) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "insert into coupon(seller_id,original_price,current_price,description,public_time,update_time) values(?,?,?,?,?,?)";
		ResultSet rs = null;
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, coupon.getSeller_id());
			preparedStatement.setInt(2, coupon.getOriginal_price());
			preparedStatement.setInt(3, coupon.getCurrent_price());
			preparedStatement.setString(4, coupon.getDescription());
			preparedStatement.setString(5, coupon.getPublic_time());
			preparedStatement.setString(6, coupon.getUpdate_time());
			preparedStatement.execute();
			rs = preparedStatement.getGeneratedKeys();
			while (rs.next()) {
				coupon.setId(rs.getInt(1));
				System.out.println(coupon.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(rs, preparedStatement, connection);
		return coupon;
	}

	@Override
	public Coupon updateCoupon(Coupon coupon) {
		Coupon coupon2 = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "update coupon set original_price=?,current_price=?,description=?,update_time=? where id =?";
		ResultSet rs = null;
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, coupon.getOriginal_price());
			preparedStatement.setInt(2, coupon.getCurrent_price());
			preparedStatement.setString(3, coupon.getDescription());
			preparedStatement.setString(4, coupon.getUpdate_time());
			preparedStatement.setInt(5, coupon.getId());
			int a = preparedStatement.executeUpdate();
			if (a > 0)
				coupon2 = coupon;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(rs, preparedStatement, connection);
		return coupon2;
	}

	@Override
	public boolean deleteCouponByid(int id) {
		boolean isSuccess = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "delete from coupon where id=? limit 1";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			int m = preparedStatement.executeUpdate();
			if (m > 0)
				isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.release(null, preparedStatement, connection);
		}
		return isSuccess;
	}

}
