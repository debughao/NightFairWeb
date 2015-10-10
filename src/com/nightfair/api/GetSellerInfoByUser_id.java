package com.nightfair.api;

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
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetSellerInfoByUser_id
 */
@WebServlet("/apis/sellerinfo/getbyid")
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
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		JSONObject jsonObject = new JSONObject();
		int state = 0;// 默认设置为成功
		//int user_id= Integer.parseInt(request.getParameter("user_id"));
		PrintWriter pw = response.getWriter();
		SellerDao sellerDao = DaoFactory.getInstance().getSellerDao();
		SellerInfo sellerInfo = null;
		sellerInfo = sellerDao.getSellerInfoByUser_id(201501);		
		jsonObject.put("data", sellerInfo);
		jsonObject.put("state", state);
		pw.write(jsonObject.toString());
		System.out.println(jsonObject);
		pw.close();
	}

}
