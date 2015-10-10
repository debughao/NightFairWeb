package com.nightfair.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nightfair.dao.Interface.IClassifyDao;
import com.nightfair.uitl.DBUtils;
import com.nightfair.vo.Classify;

public class ClassifyDao implements IClassifyDao {

	@Override
	public ArrayList<Classify> getAllClassify() {

		ArrayList<Classify> classifies = new ArrayList<Classify>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select * from classify";
		connection = DBUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String id = String.valueOf(resultSet.getInt("id"));
				String classify_name = resultSet.getString("classify_name");
				Classify classify = new Classify(id, classify_name);
				classifies.add(classify);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.release(resultSet, preparedStatement, connection);
		}
  
		return classifies;

	}



	@Override
	public boolean updateClassifyByname(String classify_name) {

		return false;
	}



	@Override
	public boolean addClassify(Classify classify) {

		return false;
	}

}
