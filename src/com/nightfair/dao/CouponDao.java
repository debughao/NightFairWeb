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
import com.nightfair.vo.SellerAndCoupon;

public class CouponDao implements ICouponDao {

	@Override
	public ArrayList<Coupon> getAllCouponBysellerId(int seller_id) {
		ArrayList<Coupon> couponsList = new ArrayList<Coupon>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String sql = "select * from coupon,manage_coupon where coupon.id=manage_coupon.coupon_id and seller_id= ?";
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
				int isguess = rs.getInt("isguess");
				int isrecoomend = rs.getInt("isrecoomend");
				Coupon coupon = new Coupon(id, seller_id, original_price, current_price, description, public_time,
						update_time, seller_counts, state, isguess, isrecoomend);
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
	public ArrayList<Coupon> getAllGeuessCouponBysellerId(int seller_id) {
		ArrayList<Coupon> couponsList = new ArrayList<Coupon>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String sql = "select * from coupon,manage_coupon where coupon.id=manage_coupon.coupon_id and isguess=1 and seller_id= ?";
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
				int isguess = rs.getInt("isguess");
				int isrecoomend = rs.getInt("isrecoomend");
				Coupon coupon = new Coupon(id, seller_id, original_price, current_price, description, public_time,
						update_time, seller_counts, state, isguess, isrecoomend);
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
	public ArrayList<Coupon> getAllRecommandCouponBysellerId(int seller_id) {
		ArrayList<Coupon> couponsList = new ArrayList<Coupon>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String sql = "select * from coupon,manage_coupon where coupon.id=manage_coupon.coupon_id and isrecoomend=1 and seller_id= ?";
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
				int isguess = rs.getInt("isguess");
				int isrecoomend = rs.getInt("isrecoomend");
				Coupon coupon = new Coupon(id, seller_id, original_price, current_price, description, public_time,
						update_time, seller_counts, state, isguess, isrecoomend);
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
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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

	@Override
	public ArrayList<SellerAndCoupon> getAllCoupon( String parm) {
		ArrayList<SellerAndCoupon> sellerAndCoupons = new ArrayList<SellerAndCoupon>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select * FROM view_seller_coupon GROUP BY view_seller_coupon.user_id  ";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int user_id = resultSet.getInt("user_id");
				String seller_name = resultSet.getString("seller_name");
				String phone = resultSet.getString("seller_phone");
				String province = resultSet.getString("province");
				String city = resultSet.getString("city");
				String arer = resultSet.getString("arer");
				String street = resultSet.getString("street");
				String latitude = resultSet.getString("latitude");
				String longitude = resultSet.getString("longitude");
				String classify_id = resultSet.getString("classify_id");
				String img = resultSet.getString("img");
				double rank = resultSet.getDouble("rank");
				CouponDao couponDao = DaoFactory.getInstance().getCouponDao();
				ArrayList<Coupon> coupons = new ArrayList<Coupon>();
				if (parm.equals("getGuessCoupon")) {
					coupons = couponDao.getAllGeuessCouponBysellerId(id);
				}else if (parm.equals("getRecommandCoupon")) {
					coupons = couponDao.getAllRecommandCouponBysellerId(id);
				}else if (parm.equals("getAllCoupon")) {
					coupons = couponDao.getAllCouponBysellerId(id);
				}			
				SellerAndCoupon sellerAndCoupon = new SellerAndCoupon(id, user_id, seller_name, phone, province, city,
						arer, street, latitude, longitude, rank, classify_id, img, coupons);
				sellerAndCoupons.add(sellerAndCoupon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.release(resultSet, preparedStatement, connection);
		}
		return sellerAndCoupons;
	}



}
