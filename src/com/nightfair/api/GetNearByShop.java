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
import com.nightfair.dao.DaoFactory;
import com.nightfair.dao.SellerDao;
import com.nightfair.vo.Nearby;
import com.nightfair.vo.SellerInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetNearByShop
 */
@WebServlet("/buyer/getNearbyshop")
public class GetNearByShop extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetNearByShop() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		JSONObject jsonObject = new JSONObject();
		int status = 500;
		String result = "无权访问";
		String key = request.getParameter("key");
		System.out.println("key值" + key);
		if ("a2e22c742b952403".equals(key)) {
			BuyerDao buyerDao = DaoFactory.getInstance().getBuyerDao();
			String action = request.getParameter("action");
			System.out.println("操作"+action);
			if ("neaby".equals(action)) {
				ArrayList<Nearby> nearbies = buyerDao.selectAllShop();
				if (nearbies.size() > 0) {
					status = 200;
					result = "获取数据成功";
					JSONArray jsonArray = JSONArray.fromObject(nearbies);
					jsonObject.put("data", jsonArray);
				}
			} else if ("serach".equals(action)) {
				SellerDao sellerDao = DaoFactory.getInstance().getSellerDao();
				String sellername = request.getParameter("sellername");
				System.out.println("查询"+sellername);
				ArrayList<SellerInfo> sellerInfos = sellerDao.FuzzyQuery(sellername);
				if (sellerInfos.size() > 0) {
					status = 200;
					result = "获取数据成功";
					JSONArray jsonArray = JSONArray.fromObject(sellerInfos);
					jsonObject.put("data", jsonArray);
				}
			}
		}
		jsonObject.put("state", status);
		jsonObject.put("result", result);
		PrintWriter pw = response.getWriter();
		System.out.println(jsonObject);
		pw.write(jsonObject.toString());
		pw.close();
	}

}
