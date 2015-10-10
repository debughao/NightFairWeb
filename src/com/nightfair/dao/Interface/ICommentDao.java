package com.nightfair.dao.Interface;

import java.util.ArrayList;

import com.nightfair.vo.Comment;

public interface ICommentDao {
	public abstract ArrayList<Comment> getCommentBySellerid(int seller_id,int pageNum,int pageNnow );
	public abstract  ArrayList<Integer> getCommentNumSellerid(int seller_id,int pageNum);
}
