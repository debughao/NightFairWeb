package com.nightfair.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.nightfair.dao.Interface.IOrderDao;
import com.nightfair.uitl.Couponcode;
import com.nightfair.uitl.DBUtils;
import com.nightfair.vo.Buyer_order;
import com.nightfair.vo.Coupon;
import com.nightfair.vo.Order;
import com.nightfair.vo.SellerInfo;

public class OrderDao implements IOrderDao {

	@Override
	public int insertOrder(Order Order) {
		// TODO Auto-generated method stub
		int f = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String sql = "insert into orders(order_id,seller_id,user_id,coupon_id,order_time,amount,num) "
				+ "values(?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE order_id =VALUES(order_id),seller_id =VALUES(seller_id),"
				+ "user_id =VALUES(user_id),coupon_id =VALUES(coupon_id),order_time =VALUES(order_time),amount =VALUES(amount),num =VALUES(num)";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, Order.getOrder_id());
			preparedStatement.setInt(2, Order.getSeller_id());
			preparedStatement.setInt(3, Order.getUser_id());
			preparedStatement.setInt(4, Order.getCoupon_id());
			preparedStatement.setString(5, Order.getOrder_time());
			preparedStatement.setDouble(6, Order.getAmount());
			preparedStatement.setString(7, Order.getNum());
			preparedStatement.execute();
			rs = preparedStatement.getGeneratedKeys();
			while (rs.next()) {
				f = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.release(rs, preparedStatement, connection);
			System.out.println("提交订单后获取的订单号：" + f);
		}
		return f;
	}

	@Override
	public int selectNonPaymentOrderid(int user_id, int coupon_id) {
		// TODO Auto-generated method stub
		int order_id = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT order_id FROM orders WHERE user_id = ? AND coupon_id = ? AND state = 0";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			preparedStatement.setInt(2, coupon_id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				order_id = resultSet.getInt("order_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(resultSet, preparedStatement, connection);

		return order_id;
	}

	@Override
	public boolean updateOrder(int orderid, String way) {
		// TODO Auto-generated method stub
		boolean isSuccess = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "update orders set state=? where order_id =? limit 1 ";
		ResultSet rs = null;
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			if ("pay".equals(way)) {
				preparedStatement.setInt(1, 1);
			} else if ("consume".equals(way)) {
				preparedStatement.setInt(1, 2);
			} else if ("refues".equals(way)) {
				preparedStatement.setInt(1, 3);
			} else if ("comment".equals(way)) {
				preparedStatement.setInt(1, 4);
			}
			preparedStatement.setInt(2, orderid);
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
	public ArrayList<Buyer_order> selectOrders(int user_id, String stated, String type) {
		int state = Integer.parseInt(stated);
		ArrayList<Buyer_order> buyer_orders = new ArrayList<Buyer_order>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM view_orders WHERE buyer_userid = ? AND state = ?";
		String sql2 = "SELECT * FROM view_orders WHERE buyer_userid = ?";
		connection = DBUtils.getConnection();
		try {
			if ("all".equals(type)) {
				preparedStatement = connection.prepareStatement(sql2);
				preparedStatement.setInt(1, user_id);
			} else if ("single".equals(type)) {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, user_id);
				preparedStatement.setInt(2, state);				
			}						
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int order_id = resultSet.getInt("order_id");
				int seller_id = resultSet.getInt("id");
				int coupon_id = resultSet.getInt("coupon_id");
				String order_time = resultSet.getString("order_time");
				String num = resultSet.getString("num");
				int states = resultSet.getInt("state");
				double amount = resultSet.getDouble("amount");
				String coupon_num = resultSet.getString("coupon_num");
				String seller_name = resultSet.getString("seller_name");
				String phone = resultSet.getString("seller_phone");
				String province = resultSet.getString("province");
				String city = resultSet.getString("city");
				String arer = resultSet.getString("arer");
				String street = resultSet.getString("street");
				String latitude = resultSet.getString("latitude");
				String longitude = resultSet.getString("longitude");
				double rank = resultSet.getDouble("rank");
				String img = resultSet.getString("img");
				int original_price = resultSet.getInt("original_price");
				int current_price = resultSet.getInt("current_price");
				String description = resultSet.getString("description");
				int seller_counts = resultSet.getInt("seller_counts");
				int isrefues = resultSet.getInt("isrefues");
				Order order = new Order(order_id, seller_id, user_id, coupon_id, order_time, num, amount, states,
						coupon_num, isrefues);
				SellerInfo sellerInfo = new SellerInfo(seller_id, 0, seller_name, phone, province, city, arer, street,
						latitude, longitude, rank, null, img);
				Coupon coupon = new Coupon(coupon_id, seller_id, original_price, current_price, description,
						seller_counts);
				Buyer_order buyer_order = new Buyer_order(sellerInfo, order, coupon);
				buyer_orders.add(buyer_order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(resultSet, preparedStatement, connection);

		return buyer_orders;
	}

	@Override
	public boolean cancalOrder(int orderid) {
		// TODO Auto-generated method stub
		boolean isSuccess = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "delete from orders where order_id =? limit 1 ";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, orderid);
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
	public boolean createCouponcode(int orderid) {
		// TODO Auto-generated method stub
		boolean isSuccess = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "update orders set coupon_num=? where order_id =? limit 1 ";
		ResultSet rs = null;
		String CouponCode = Couponcode.CreateCouponcode();
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, CouponCode);
			preparedStatement.setInt(2, orderid);
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

}
