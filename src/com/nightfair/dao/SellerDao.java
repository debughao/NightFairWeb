package com.nightfair.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.nightfair.dao.Interface.ISellerDao;
import com.nightfair.uitl.DBUtils;
import com.nightfair.uitl.RandomNickname;
import com.nightfair.vo.SellerInfo;

public class SellerDao implements ISellerDao {

	@Override
	public boolean updateSellerInfo(SellerInfo sellerInfo) {
		boolean isSuccess = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		System.out.println(sellerInfo);
		String sql = "update seller_info set seller_name=?,seller_phone=?,province=?, city=?,  arer=?,"
				+ " street=?,latitude=?, longitude=?,  classify_id=?  where user_id=? ";
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
		String sql = "SELECT * FROM seller_info WHERE user_id=? LIMIT 1";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, User_id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int  id = resultSet.getInt("id");
				int  user_id = resultSet.getInt("user_id");
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

	@Override
	public boolean insertBuyerInfo(int seller_id) {
		// TODO Auto-generated method stub
		boolean f = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "insert into seller_info(user_id,seller_name) values(?,?)";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);		
			RandomNickname rNickname = new RandomNickname();
			String nickname = "用户" + 2 * rNickname.getRandomHan();
			preparedStatement.setInt(1,seller_id );
			preparedStatement.setString(2,  nickname);
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
	public ArrayList<SellerInfo> FuzzyQuery(String sellername) {
		// TODO Auto-generated method stub
		 ArrayList<SellerInfo> sellerInfos=new ArrayList<SellerInfo>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT *FROM seller_info WHERE seller_name like'%" + sellername + "%' ";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String seller_name=resultSet.getString("seller_name");
				SellerInfo sellerInfo=new SellerInfo(id, seller_name);
				sellerInfos.add(sellerInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(resultSet, preparedStatement, connection);

		return sellerInfos;
	}
}
