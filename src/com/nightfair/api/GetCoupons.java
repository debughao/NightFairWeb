package com.nightfair.api;

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
import com.nightfair.vo.SellerAndCoupon;
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
			if ("getGuessCoupon".equals(action)) {
				sellerAndCoupons = couponDao.getAllCoupon(action);
				if (sellerAndCoupons.size() != 0) {				
					status = 200;
					result = "请求数据成功！";
					JSONArray jsonArray = JSONArray.fromObject(sellerAndCoupons);
					jsonObject.put("data", jsonArray);
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
