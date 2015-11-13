package com.nightfair.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nightfair.dao.BuyerDao;
import com.nightfair.dao.CouponDao;
import com.nightfair.dao.DaoFactory;
import com.nightfair.uitl.Datetime;
import com.nightfair.vo.Collection;
import com.nightfair.vo.SellerAndCoupon;
import com.nightfair.vo.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetCoupons
 */
@WebServlet("/buyer/getcoupons")
public class GetCoupons extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetCoupons() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		JSONObject jsonObject = new JSONObject();
		int status = 500;
		String result = "无权访问";
		PrintWriter pw = response.getWriter();
		CouponDao couponDao = DaoFactory.getInstance().getCouponDao();
		ArrayList<SellerAndCoupon> sellerAndCoupons = null;
		String key = request.getParameter("key");
		if ("a2e22c742b952403".equals(key)) {
			String action = request.getParameter("action");
			System.out.println("操作"+action);
			//String action="getallbysellerrid";
			if ("getGuessCoupon".equals(action)) {
				sellerAndCoupons = couponDao.getAllCoupon(action);
				if (sellerAndCoupons.size() != 0) {
					status = 200;
					result = "请求数据成功！";
					JSONArray jsonArray = JSONArray.fromObject(sellerAndCoupons);
					jsonObject.put("data", jsonArray);
				}else {
					status = 404;
					result = "获取数据失败！";
				}
			} 
			else if ("getRecommandCoupon".equals(action)) {
				sellerAndCoupons = couponDao.getAllCoupon(action);
				if (sellerAndCoupons.size() != 0) {				
					status = 200;
					result = "请求数据成功！";
					JSONArray jsonArray = JSONArray.fromObject(sellerAndCoupons);
					jsonObject.put("data", jsonArray);
				}else {
					status = 404;
					result = "获取数据失败！";
				}
			}else if ("getallbysellerrid".equals(action)) {	
				int seller_id=Integer.parseInt(request.getParameter("seller_id"));
				//sellerAndCoupons = couponDao.getAllCouponBySeller_id(2);
				SellerAndCoupon	sellerAndCoupon = couponDao.getAllCouponBySeller_id(seller_id);
				if (sellerAndCoupon!=null) {				
					status = 200;
					result = "请求数据成功！";			
					jsonObject.put("data", sellerAndCoupon);
				}else {
					status = 404;
					result = "获取数据失败！";
				}
			}
			else if ("my".equals(action)) {
				User user = (User) request.getSession().getAttribute("buyer");				
				if (user != null) {
					int user_id = user.getU_id();
					String way = request.getParameter("way");
					String couponid = request.getParameter("couponid");
					BuyerDao buyerDao = DaoFactory.getInstance().getBuyerDao();
					System.out.println("action操作"+action+"----"+way+"-----------couponid"+couponid);
					boolean isSuccess;
					Collection Collection;
					int coupon_id = Integer.parseInt(couponid);
					if ("add".equals(way)) {
						String seller_id = request.getParameter("seller_id");
						int sellerid = Integer.parseInt(seller_id);
						String time = Datetime.getNow();
						System.out.println("action操作"+action+"----"+"卖家id"+seller_id+way+"-----------couponid"+couponid);
						Collection = new Collection(0, user_id, sellerid, time, coupon_id);
					} else {
						Collection = new Collection(user_id, coupon_id);
					}
					isSuccess = buyerDao.isCollectionCoupon(Collection, way);
					System.out.println(isSuccess);
					if (isSuccess) {
						if ("add".equals(way)) {
							status = 200;
							result = "收藏成功！";
						}else if ("sub".equals(way)) {
							status = 201;
							result = "取消收藏成功！";	
						}else if ("select".equals(way)) {
							status = 200;
							result = "已收藏！";
						}												
					}else {
						if ("select".equals(way)) {
							status = 201;
							result = "未收藏！";
						}
						else {
							status = 401;
							result = "操作失败！";
						}
					} 
				} else {
					status = 405;// 身份过期
					result = "登录身份过期，请重新登录";
				}
			}
		}
		jsonObject.put("state", status);
		jsonObject.put("result", result);
		pw.write(jsonObject.toString());
		System.out.println(jsonObject);
		pw.close();
	}

}
