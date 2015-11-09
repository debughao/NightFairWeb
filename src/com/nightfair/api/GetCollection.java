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
import com.nightfair.vo.BuyerCollection;
import com.nightfair.vo.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetCollection
 */
@WebServlet("/buyer/getcollection")
public class GetCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetCollection() {
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
		User user = (User) request.getSession().getAttribute("buyer");
		String key = request.getParameter("key");
		if ("a2e22c742b952403".equals(key)) {
			// if ("a2e22c742b952403".equals("a2e22c742b952403")) {//测试数据
			if (user != null) {
				// if (user == null) {
				int user_id = user.getU_id();
				// int user_id=201547;//测试数据
				BuyerDao buyerDao = DaoFactory.getInstance().getBuyerDao();
				ArrayList<BuyerCollection> buyerCollections = buyerDao.selectCollection(user_id);
				if (buyerCollections.size() > 0) {
					JSONArray jsonArray = JSONArray.fromObject(buyerCollections);
					jsonObject.put("data", jsonArray);
					status = 200;
					result = "查询成功！";
				} else {
					status = 201;
					result = "暂无收藏！";
				}
			} else {
				status = 405;// 身份过期
				result = "登录身份过期，请重新登录";
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

