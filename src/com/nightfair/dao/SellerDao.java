package com.nightfair.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nightfair.dao.Interface.ISellerDao;
import com.nightfair.uitl.DBUtils;
import com.nightfair.vo.SellerInfo;

public class SellerDao implements ISellerDao {

	@Override
	public boolean updateSellerInfo(SellerInfo sellerInfo) {
		boolean isSuccess = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		System.out.println(sellerInfo);
		String sql = "update user,seller_info set nickname=?,phone=?,province=?, city=?,  arer=?,"
				+ " street=?,latitude=?, longitude=?,  classify_id=?  where user_id=? and user.u_id=seller_info.user_id ";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, sellerInfo.getSeller_name());
			preparedStatement.setString(2, sellerInfo.getPhone());
			preparedStatement.setString(3, sellerInfo.getProvince());
			preparedStatement.setString(4, sellerInfo.getCity());
			preparedStatement.setString(5, sellerInfo.getArer());
			preparedStatement.setString(6, sellerInfo.getStreet());
			preparedStatement.setString(7, sellerInfo.getLatitude());
			preparedStatement.setString(8, sellerInfo.getLongitude());
			preparedStatement.setInt(9, Integer.parseInt(sellerInfo.getClassify_id()));
			preparedStatement.setInt(10,sellerInfo.getUser_id());
			int m = preparedStatement.executeUpdate();
			if (m > 0) {
				isSuccess = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			DBUtils.release(resultSet, preparedStatement, connection);
		}
		return isSuccess;
	}

	@Override
	public SellerInfo getSellerInfoByUser_id(int User_id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		SellerInfo sellerInfo = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM USER,seller_info WHERE user.u_id= seller_info.user_id AND u_id=? LIMIT 1";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, User_id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int  id = resultSet.getInt("id");
				int  user_id = resultSet.getInt("u_id");
				String seller_name = resultSet.getString("nickname");
				String phone = resultSet.getString("Phone");
				String province = resultSet.getString("province");
				String city = resultSet.getString("city");
				String arer = resultSet.getString("arer");
				String street = resultSet.getString("street");
				String latitude = resultSet.getString("latitude");
				String longitude = resultSet.getString("longitude");
				String classify_id = resultSet.getString("classify_id");
				String img = resultSet.getString("img");
				double rank = resultSet.getDouble("rank");
				sellerInfo = new SellerInfo(id, user_id, seller_name, phone, province, city, arer, street, latitude,
						longitude, rank, classify_id, img);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(resultSet, preparedStatement, connection);
		return sellerInfo;
	}

	@Override
	public boolean existSeller(String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean isExist = false;
		String sql = "select * from seller_info where id =? limit 1";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				isExist = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(resultSet, preparedStatement, connection);
		return isExist;
	}

	@Override
	public boolean uploadshopimage(String imageur, int userid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean isSuccess = false;
		String sql = "update seller_info set img=? where user_id=? limit 1";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, imageur);
			preparedStatement.setInt(2, userid);
			int m = preparedStatement.executeUpdate();
			if (m > 0) {
				isSuccess = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtils.release(resultSet, preparedStatement, connection);
		return isSuccess;
	}

	@Override
	public int getSelleridByUid(int User_id) {
		int seller_id = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select id from seller_info where user_id =? limit 1";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, User_id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				seller_id = resultSet.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(resultSet, preparedStatement, connection);

		return seller_id;
	}

}
