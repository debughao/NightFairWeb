package com.nightfair.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nightfair.dao.Interface.IUserDao;
import com.nightfair.uitl.DBUtils;
import com.nightfair.uitl.MD5Util;
import com.nightfair.uitl.Matches;
import com.nightfair.uitl.RandomNickname;
import com.nightfair.vo.SellerInfo;
import com.nightfair.vo.User;

/**
 * 
 * @ClassName: UserDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author debughao
 * @date 2015年9月18日
 */
public class UserDao implements IUserDao {

	@Override
	public SellerInfo getUserInfoByUser_id(int User_id) {
		return null;
	}

	@Override
	public boolean existUserByUsername(String parameter) {
		boolean f = true;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String sql = "select * from user where username= ? limit 1 ";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, parameter);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				f = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.release(rs, preparedStatement, connection);
			System.out.println(f);
		}
		return f;
	}

	@Override
	public boolean existUserByPhone(String parameter) {

		boolean f = true;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String sql = "select * from user where phone= ? limit 1 ";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, parameter);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				f = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.release(rs, preparedStatement, connection);
			System.out.println(f);
		}
		return f;

	}

	@Override
	public boolean existUserByEmail(String parameter) {

		boolean f = true;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String sql = "select * from user where email= ? limit 1 ";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, parameter);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				f = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.release(rs, preparedStatement, connection);
			System.out.println(f);
		}
		return f;

	}

	@Override
	public boolean regiserUser(User uer) {
		boolean f = true;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "insert into user(username,nickname,email,phone,password,type) values(?,?,?,?,?,?)";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			RandomNickname rNickname = new RandomNickname();
			String nickname = "用户" + 2 * rNickname.getRandomHan();
			preparedStatement.setString(1, uer.getUsername());
			preparedStatement.setString(2, nickname);
			preparedStatement.setString(3, uer.getEmail());
			preparedStatement.setString(4, uer.getPhone());
			preparedStatement.setString(5, MD5Util.MD5(uer.getPassword()));
			preparedStatement.setInt(6, uer.getType());
			if (preparedStatement.executeUpdate() > 0) {
				f = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.release(null, preparedStatement, connection);
			System.out.println(f);
		}
		return f;
	}

	@Override
	public User isPassLogin(String username ,String password,String type) {
		User f = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String usernames=null;
		String emails=null;
		String phones=null;
		if(Matches.isUsename(username)){
			usernames=username;
		}else if(Matches.isPhone(username)){
			phones=username;
		}else if(Matches.isEmail(username)){
			emails=username;
		}
		String sql = "SELECT * FROM USER WHERE(username=? OR email=? OR phone=?) AND PASSWORD=? and type=? ";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usernames);
			preparedStatement.setString(2,emails);
			preparedStatement.setString(3, phones);
			preparedStatement.setString(4, password);
			preparedStatement.setString(5, type);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int u_id = rs.getInt("u_id");
				f =new User(u_id, null, null, null, 1);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.release(rs, preparedStatement, connection);
		}
	
		return f;

	}
}
