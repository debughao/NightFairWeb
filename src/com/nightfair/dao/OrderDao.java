package com.nightfair.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nightfair.dao.Interface.IOrderDao;
import com.nightfair.uitl.DBUtils;
import com.nightfair.vo.Order;

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
	public boolean updateOrder(int orderid,String way) {
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
			}else {
				preparedStatement.setInt(1, 2);
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

}
