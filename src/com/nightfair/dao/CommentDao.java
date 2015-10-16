package com.nightfair.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nightfair.dao.Interface.ICommentDao;
import com.nightfair.uitl.DBUtils;
import com.nightfair.uitl.PageNumUitl;
import com.nightfair.vo.Comment;

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
		String sql = "select * from comments,buyer_info where comments.user_id=buyer_info.user_id and seller_id=? ORDER BY comments.id ASC limit ?,?";
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

}
