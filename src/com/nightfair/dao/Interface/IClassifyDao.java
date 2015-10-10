package com.nightfair.dao.Interface;

import java.util.ArrayList;

import com.nightfair.vo.Classify;

public interface IClassifyDao {
	public abstract boolean addClassify(Classify classify);
	public abstract boolean updateClassifyByname(String classify_name);
	public abstract ArrayList<Classify> getAllClassify();
}
