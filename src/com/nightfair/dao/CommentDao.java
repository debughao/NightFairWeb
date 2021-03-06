package com.nightfair.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.nightfair.dao.Interface.ICommentDao;
import com.nightfair.uitl.DBUtils;
import com.nightfair.uitl.PageNumUitl;
import com.nightfair.vo.BuyerComments;
import com.nightfair.vo.Comment;
import com.nightfair.vo.Comments;
import com.nightfair.vo.SellerCommentsBuyer;

/**
 * 
 * @ClassName: CommentDao
 * @Description: TODO(根据商家号 获取其评论的总条数和评论信息)
 * @author debughao
 * @date 2015年9月16日
 */
public class CommentDao implements ICommentDao {

	@Override
	public ArrayList<Comment> getCommentBySellerid(int seller_id, int pageNum, int pageNow) {
		ArrayList<Comment> couponsList = new ArrayList<Comment>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int m = (pageNow - 1) * pageNum;
		System.out.println(m);
		int n = pageNum;
		String sql = "select * from comments,buyer_info where comments.buyer_id=buyer_info.user_id and seller_id=? ORDER BY comments.id ASC limit ?,?";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, seller_id);
			preparedStatement.setInt(2, m);
			preparedStatement.setInt(3, n);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int seller_id1 = rs.getInt("seller_id");
				String nickname = rs.getString("nickname");
				int user_id = rs.getInt("user_id");
				double grade = rs.getDouble("grade");
				String comment = rs.getString("comment");
				String time = rs.getString("time");
				Comment comments = new Comment(id, grade, comment, time, seller_id1, user_id, nickname);
				couponsList.add(comments);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtils.release(rs, preparedStatement, connection);
		}
		return couponsList;
	}

	@Override
	public SellerCommentsBuyer getSellerCommentsBuyerBySellerid(int seller_id, int pageNum, int pageNow) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		SellerCommentsBuyer sellerCommentsBuyer = null;
		String sql = "select * from view_comment where seller_id=? ";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, seller_id);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				double rank = rs.getDouble("rank");
				ArrayList<Comments> comments = null;
				CommentDao commentDao = DaoFactory.getInstance().getCommentDao();
				comments = commentDao.getCommentsBySellerid(seller_id, pageNum, pageNow);
				sellerCommentsBuyer = new SellerCommentsBuyer(seller_id, rank, comments);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtils.release(rs, preparedStatement, connection);
		}
		return sellerCommentsBuyer;
	}

	@Override
	public ArrayList<Comments> getCommentsBySellerid(int seller_id, int pageNum, int pageNow) {
		ArrayList<Comments> mComments = new ArrayList<Comments>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int m = (pageNow - 1) * pageNum;
		System.out.println(m);
		int n = pageNum;
		String sql = "select * from view_comment where seller_id=? ORDER BY id ASC limit ?,?";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, seller_id);
			preparedStatement.setInt(2, m);
			preparedStatement.setInt(3, n);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				double grade = rs.getDouble("grade");
				String comment = rs.getString("comment");
				String time = rs.getString("time");
				String buyerimg = rs.getString("image");
				String nickname = rs.getString("nickname");
				int buyer_id = rs.getInt("buyer_id");
				Comments comments=new Comments(id, grade, comment, time, buyer_id, nickname, buyerimg);
				mComments.add(comments);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtils.release(rs, preparedStatement, connection);
		}
		return mComments;
	}

	@Override
	public ArrayList<Integer> getCommentNumSellerid(int seller_id, int pageNum) {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		int num = 1;
		num = PageNumUitl.getNum("comments", "seller_id", seller_id);
		System.out.println("总条数" + num);
		nums.add(num);
		int yema = 0;
		if (num % pageNum == 0) {
			yema = num / pageNum;
		}
		if (num % pageNum != 0) {
			yema = num / pageNum + 1;
		}
		System.out.println("总页码" + yema);
		nums.add(yema);
		return nums;
	}

	@Override
	public boolean insertCommnet(Comment comment) {
		// TODO Auto-generated method stub
		boolean  f =false;
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "insert into comments(buyer_id,grade,comment,time,seller_id) values(?,?,?,?,?)";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, comment.getUser_id());
			preparedStatement.setDouble(2, comment.getGrade());
			preparedStatement.setString(3, comment.getComment());
			preparedStatement.setString(4, comment.getTime());
			preparedStatement.setInt(5, comment.getSeller_id());		
			int n =preparedStatement.executeUpdate();
			if (n>0) {
				f=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.release(rs, preparedStatement, connection);
		}
		return f;
	}

	@Override
	public ArrayList<BuyerComments> getCommentsByBuyeruid(int buyer_uid) {
		// TODO Auto-generated method stub
		ArrayList<BuyerComments> mComments = new ArrayList<BuyerComments>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String sql = "select * from view_comment where user_id=? ORDER BY id ASC ";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, buyer_uid);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int seller_id = rs.getInt("buyer_id");
				double grade = rs.getDouble("grade");
				String comment = rs.getString("comment");
				String time = rs.getString("time");			
				String seller_name=rs.getString("seller_name");	;
				BuyerComments comments=new BuyerComments(id, seller_id, grade, comment, time,seller_name);
				mComments.add(comments);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtils.release(rs, preparedStatement, connection);
		}
		return mComments;
	}

	@Override
	public int getCommnetNumBySellerid(int seller_id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int n=0;
		String sql = "SELECT COUNT(id) AS num FROM comments WHERE seller_id= ? GROUP BY seller_id; ";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, seller_id);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				n=rs.getInt("num");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtils.release(rs, preparedStatement, connection);
		}
		return n;
	}

}
