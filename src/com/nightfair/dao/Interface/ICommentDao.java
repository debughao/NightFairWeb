package com.nightfair.dao.Interface;

import java.util.ArrayList;

import com.nightfair.vo.BuyerComments;
import com.nightfair.vo.Comment;
import com.nightfair.vo.Comments;
import com.nightfair.vo.SellerCommentsBuyer;

public interface ICommentDao {
	public abstract ArrayList<Comment> getCommentBySellerid(int seller_id,int pageNum,int pageNnow );
	public abstract  ArrayList<Integer> getCommentNumSellerid(int seller_id,int pageNum);
	public abstract SellerCommentsBuyer getSellerCommentsBuyerBySellerid(int seller_id,int pageNum,int pageNnow );
	public abstract ArrayList<Comments> getCommentsBySellerid(int seller_id,int pageNum,int pageNnow );
	public abstract boolean insertCommnet(Comment comment);
	public abstract ArrayList<BuyerComments> getCommentsByBuyeruid(int buyer_uid);
	public abstract int  getCommnetNumBySellerid(int seller_id);
}
