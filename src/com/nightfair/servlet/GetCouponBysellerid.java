package com.nightfair.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nightfair.dao.CouponDao;
import com.nightfair.dao.DaoFactory;
import com.nightfair.dao.SellerDao;
import com.nightfair.uitl.Datetime;
import com.nightfair.vo.Coupon;
import com.nightfair.vo.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 
 * GetCouponBysellerid
 * 
 * 2015年9月12日 下午8:48:35
 * 
 * @version 1.0.0
 *
 */
@WebServlet("/Seller/GetCoupon")
public class GetCouponBysellerid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetCouponBysellerid() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		JSONObject jsonObject = new JSONObject();
		int status = 404;
		String result_status = "获取数据失败";
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			SellerDao sellerDao = DaoFactory.getInstance().getSellerDao();
			int seller_id = sellerDao.getSelleridByUid(user.getU_id());
			System.out.println("商家id" + seller_id);
			String action = request.getParameter("action");
			System.out.println("操作:" + action);
			CouponDao couponDao = DaoFactory.getInstance().getCouponDao();
			if ("select".equals(action)) {
				ArrayList<Coupon> couponsList = new ArrayList<Coupon>();
				couponsList = couponDao.getAllCouponBysellerId(seller_id);
				JSONArray jsonArray = JSONArray.fromObject(couponsList);
				jsonObject.put("data", jsonArray);
				status = 200;
				result_status = "请求数据成功！";
			} else if ("add".equals(action)) {
				int original_price = Integer.parseInt(request.getParameter("original_price"));
				int current_price = Integer.parseInt(request.getParameter("current_price"));
				String description = request.getParameter("description");
				Coupon coupon1 = new Coupon(seller_id, original_price, current_price, description, Datetime.getNow(),
						Datetime.getNow(), 0);
				Coupon coupon = couponDao.addCoupon(coupon1);
				jsonObject.put("coupon", coupon);
				status = 200;
				result_status = "添加数据成功！";

			} else if ("update".equals(action)) {
				System.out.println(request.getParameter("id"));
				int id = Integer.parseInt(request.getParameter("id"));
				int original_price = Integer.parseInt(request.getParameter("original_price"));
				int current_price = Integer.parseInt(request.getParameter("current_price"));
				String description = request.getParameter("description");
				System.out.println(id + "---->" + original_price + "---->" + current_price + "---->" + description);
				Coupon coupon = null;
				if ((coupon = couponDao.updateCoupon(new Coupon(id, seller_id, original_price, current_price,
						description, null, Datetime.getNow(), 0))) != null) {
					status = 200;
					jsonObject.put("coupon", coupon);
					result_status = "更新数据成功！";
				} else {
					status = 500;
				}
			} else if ("delete".equals(action)) {
				System.out.println(request.getParameter("id"));
				int id = Integer.parseInt(request.getParameter("id"));
				if (couponDao.deleteCouponByid(id)) {
					status = 200;
					result_status = "刪除数据成功！";
				} else {
					status = 500;
				}
			}
		} else {
			status = 405;// 无权访问
			result_status = "无权限请求数据";
		}
		jsonObject.put("status", status);
		jsonObject.put("result_status", result_status);
		PrintWriter pw = response.getWriter();
		System.out.println(jsonObject);
		pw.write(jsonObject.toString());
		pw.close();
	}

}
