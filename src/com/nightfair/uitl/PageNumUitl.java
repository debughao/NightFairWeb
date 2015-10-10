package com.nightfair.uitl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
* @ClassName: PageNumUitl
* @Description: TODO 公共获取页码类
* @author debughao
* @date 2015年9月16日
 */
public class PageNumUitl {
	public static int pageNum=5;

	public static int getPageNum() {
		return pageNum;
	}

	public static void setPageNum(int pageNum) {
		PageNumUitl.pageNum = pageNum;
	}

	public static int getNum( String tablename,String byId, int id) {
		int num=0;
		Connection connection=DBUtils.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String sql="select count(*) from "+tablename+" where "+byId+"=?" ;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {
				num=resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.release(resultSet, preparedStatement, connection);
		}
		return num;
	}
}
