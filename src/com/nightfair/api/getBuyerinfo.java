package com.nightfair.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nightfair.dao.BuyerDao;
import com.nightfair.dao.DaoFactory;
import com.nightfair.vo.BuyerInfo;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class getBuyerinfo
 */
@WebServlet("/buyer/getbuyerinfo")
public class getBuyerinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getBuyerinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		String key = request.getParameter("key");
		System.out.println("key" + key);
		int status = 500;
		String result = "无权访问";
		JSONObject jsonObject = new JSONObject();
		if ("a2e22c742b952403".equals(key)) {
			String phone = request.getParameter("phone");
			BuyerDao buyerDao = DaoFactory.getInstance().getBuyerDao();
			BuyerInfo buyerInfo = new BuyerInfo();
			buyerInfo = buyerDao.getBuyerInfoByphone(phone);
			if (buyerInfo != null) {
				jsonObject.put("info", buyerInfo);
				status = 200;
				result = "获取数据成功";
			}
		}
		jsonObject.put("status", status);
		jsonObject.put("result", result);
		PrintWriter pw = response.getWriter();
		System.out.println(jsonObject);
		pw.write(jsonObject.toString());
		pw.close();
	}

}
