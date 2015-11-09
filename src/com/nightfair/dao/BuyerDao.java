package com.nightfair.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.nightfair.dao.Interface.IBuyerDao;
import com.nightfair.uitl.DBUtils;
import com.nightfair.vo.BuyerCollection;
import com.nightfair.vo.BuyerInfo;
import com.nightfair.vo.Collection;
import com.nightfair.vo.Coupon;
import com.nightfair.vo.Nearby;
import com.nightfair.vo.SellerInfo;

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
				int id = resultSet.getInt("id");
				String nickname = resultSet.getString("nickname");
				String sex = resultSet.getString("sex");
				String age = resultSet.getString("age");
				String star = resultSet.getString("star");
				String hometown = resultSet.getString("hometown");
				String address = resultSet.getString("address");
				String autograph = resultSet.getString("autograph");
				String image = resultSet.getString("image");
				buyerInfo = new BuyerInfo(id, String.valueOf(user_id), nickname, sex, age, star, hometown, address,
						autograph, image);
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
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, buyerInfo.getUser_id());
			preparedStatement.setString(2, buyerInfo.getNickname());
			int n = preparedStatement.executeUpdate();
			if (n > 0) {
				f = true;
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
				String user_id = resultSet.getString("user_id");
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

	@Override
	public boolean isCollectionCoupon(Collection Collection, String way) {
		// TODO Auto-generated method stub
		boolean f = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String sql = "select * from collection where buyer_uid= ? and coupon_id=? limit 1 ";
		String sql1 = "insert into collection(buyer_uid,seller_id,coupon_id,time) values(?,?,?,?)";
		String sql2 = "delete from collection where buyer_uid= ? and coupon_id=? limit 1 ";
		connection = DBUtils.getConnection();
		try {
			if ("select".equals(way)) {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, Collection.getBuyer_uid());
				preparedStatement.setInt(2, Collection.getCoupon_id());
				rs = preparedStatement.executeQuery();
				while (rs.next()) {
					f = true;
				}
			} else if ("add".equals(way)) {
				preparedStatement = connection.prepareStatement(sql1);
				preparedStatement.setInt(1, Collection.getBuyer_uid());
				preparedStatement.setInt(2, Collection.getSeller_id());
				preparedStatement.setInt(3, Collection.getCoupon_id());
				preparedStatement.setString(4, Collection.getTime());
				int n=preparedStatement.executeUpdate();
				if (n>0) {
					f = true;
				}
			} else if ("sub".equals(way)) {
				preparedStatement = connection.prepareStatement(sql2);
				preparedStatement.setInt(1, Collection.getBuyer_uid());
				preparedStatement.setInt(2, Collection.getCoupon_id());
				int n=preparedStatement.executeUpdate();
				if (n>0) {
					f = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.release(rs, preparedStatement, connection);
		}
		return f;
	}

	@Override
	public ArrayList<BuyerCollection> selectCollection(int user_id) {
		// TODO Auto-generated method stub
		ArrayList<BuyerCollection> buyerCollections = new ArrayList<BuyerCollection>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM view_collection WHERE buyer_uid = ?";
		connection = DBUtils.getConnection();
		try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, user_id);								
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int seller_id = resultSet.getInt("seller_id");
				int coupon_id = resultSet.getInt("coupon_id");
				String time = resultSet.getString("time");
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
				SellerInfo sellerInfo = new SellerInfo(seller_id, 0, seller_name, phone, province, city, arer, street,
						latitude, longitude, rank, null, img);
				Coupon coupon = new Coupon(coupon_id, seller_id, original_price, current_price, description,
						seller_counts);			
				Collection collection=new Collection(id, user_id, seller_id, time, coupon_id);
				BuyerCollection BuyerCollection = new BuyerCollection(sellerInfo, collection, coupon);
				buyerCollections.add(BuyerCollection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(resultSet, preparedStatement, connection);

		return buyerCollections;
	}

	@Override
	public ArrayList<Nearby> selectAllShop() {
		ArrayList<Nearby> nearbies=new ArrayList<Nearby>();
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM seller_info";
		try {
			connection = DBUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int seller_id = resultSet.getInt("id");
				String seller_img = resultSet.getString("img");
				String seller_name = resultSet.getString("seller_name");
				double rank = resultSet.getDouble("rank");
				String arer = resultSet.getString("arer");
				double longitude = resultSet.getDouble("longitude");
				double latitude = resultSet.getDouble("latitude");
				CommentDao commentDao=DaoFactory.getInstance().getCommentDao();		
				int  comment_num=commentDao.getCommnetNumBySellerid(seller_id);
				Nearby nearby=new Nearby(seller_id, seller_img, seller_name, rank, comment_num, arer, longitude, latitude);				
				nearbies.add(nearby);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtils.release(resultSet, preparedStatement, connection);
		return nearbies;
	}

}
