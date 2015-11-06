package com.nightfair.dao;

/**
 * 
 * @ClassName: DaoFactory
 * @Description: TODO(单例模式，使用工厂方法)
 * @author debughao
 * @date 2015年9月16日
 */
public class DaoFactory {

	private static UserDao userDao = null;
	private static ClassifyDao classifyDao = null;
	private static SellerDao sellerDao = null;
	private static GoodDao goodDao = null;
	private static CouponDao couponDao = null;
	private static CommentDao commentDao = null;
	private static BuyerDao buyerrDao = null;
	private static AccountDao accountDao=null;
	private static OrderDao orderDao=null;
	private static DaoFactory daoFactory = new DaoFactory();

	private DaoFactory() {
		userDao = new UserDao();
		classifyDao = new ClassifyDao();
		sellerDao = new SellerDao();
		goodDao = new GoodDao();
		couponDao = new CouponDao();
		commentDao = new CommentDao();
		buyerrDao = new BuyerDao();
		accountDao=new AccountDao();
		orderDao=new OrderDao();
	}

	public static DaoFactory getInstance() {
		return daoFactory;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public ClassifyDao getClassifyDao() {
		return classifyDao;
	}

	public SellerDao getSellerDao() {
		return sellerDao;
	}

	public GoodDao getGoodDao() {
		return goodDao;
	}

	public CouponDao getCouponDao() {
		return couponDao;
	}

	public CommentDao getCommentDao() {
		return commentDao;

	}

	public BuyerDao getBuyerDao() {
		return buyerrDao;

	}
	public AccountDao getAccountDao() {
		return accountDao;

	}
	public OrderDao getOrderDao() {
		return orderDao;

	}
}
