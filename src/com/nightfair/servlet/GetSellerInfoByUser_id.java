package com.nightfair.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nightfair.dao.DaoFactory;
import com.nightfair.dao.SellerDao;
import com.nightfair.vo.SellerInfo;
import com.nightfair.vo.User;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetSellerInfoByUser_id
 */
@WebServlet("/Seller/GetInfo")
public class GetSellerInfoByUser_id extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetSellerInfoByUser_id() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		JSONObject jsonObject = new JSONObject();
		int status = 404;
		String result_status = "";
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			status = 200;
			result_status = "请求数据成功！";
			SellerDao sellerDao = DaoFactory.getInstance().getSellerDao();
			int user_id = sellerDao.getSelleridByUid(user.getU_id());
			PrintWriter pw = response.getWriter();
			SellerInfo sellerInfo = null;
			sellerInfo = sellerDao.getSellerInfoByUser_id(user_id);
			jsonObject.put("data", sellerInfo);
			pw.close();
		} else {
			status = 405;// 无权访问
			result_status = "无权限请求数据！";
		}
		jsonObject.put("status", status);
		jsonObject.put("result_status", result_status);
		PrintWriter pw = response.getWriter();
		pw.write(jsonObject.toString());
		pw.close();
	}

}
