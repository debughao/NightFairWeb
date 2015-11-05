package com.nightfair.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.nightfair.dao.Interface.IAccountDao;
import com.nightfair.uitl.DBUtils;
import com.nightfair.uitl.MD5Util;

public class AccountDao implements IAccountDao {

	@Override
	public boolean insertAccount(int seller_id) {
		// TODO Auto-generated method stub
		boolean f = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "insert into account(user_id) values(?)";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, seller_id);
			int n = preparedStatement.executeUpdate();
			if (n > 0) {
				f = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.release(null, preparedStatement, connection);
			System.out.println("创建个人钱包：" + f);
		}
		return f;
	}

	@Override
	public int selectAccount(int user_id) {
		// TODO Auto-generated method stub
		int balance = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select balance from account where user_id =? limit 1";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				balance = resultSet.getInt("balance");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(resultSet, preparedStatement, connection);
		return balance;
	}

	@Override
	public boolean updateAccountbalance(int user_id, String opeartion, int money) {
		// TODO Auto-generated method stub
		boolean isSuccess = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String sql = "";
		if ("add".equals(opeartion)) {
			sql = "update account set balance=balance+? where user_id=? limit 1";
		} else if ("sub".equals(opeartion)) {
			sql = "update account set balance=balance-? where user_id=? limit 1";
		}		
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, money);
			preparedStatement.setInt(2, user_id);
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
	public boolean updatPayword(int user_id, String payword) {
		// TODO Auto-generated method stub
		boolean isSuccess = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String sql = "update account set pay_password=? where user_id =? limit 1";	
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, MD5Util.MD5(payword));
			preparedStatement.setInt(2, user_id);			
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
