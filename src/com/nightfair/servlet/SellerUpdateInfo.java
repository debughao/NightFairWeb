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
 * 
 * 
 * SellerUpdateInfo
 * 
 * 2015年9月12日 下午8:50:36
 * 
 * @version 1.0.0
 *
 */
@WebServlet("/SellerUpdateInfo")
public class SellerUpdateInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SellerUpdateInfo() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		JSONObject jsonObject = new JSONObject();
		int state = 200;// 默认设置为成功
		String result = "";		
		SellerDao sellerDao = DaoFactory.getInstance().getSellerDao();
		PrintWriter pw = response.getWriter();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			int seller_id = sellerDao.getSelleridByUid(user.getU_id());
			String action = request.getParameter("action");
			System.out.println("商家id" + seller_id);
			if ("get".equals(action)) {
				SellerInfo sellerInfo = null;
				sellerInfo = sellerDao.getSellerInfoByUser_id(user.getU_id());
				System.out.println(sellerInfo);
				jsonObject.put("data", sellerInfo);
				if(sellerInfo!=null){
				state = 200;
				result = "获取信息成功！";
				}else{
					state = 404;
					result = "查询不到信息";
			 }					
			} else if ("update".equals(action)) {
				System.out.println("操作:" + action);
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String classify = request.getParameter("classify");
				String longitude = request.getParameter("longitude");
				String latitude = request.getParameter("latitude");
				String province = request.getParameter("province");
				String city = request.getParameter("city");
				String arer = request.getParameter("arer");
				String street = request.getParameter("street");
				System.out.println(name + "-->" + phone + "-->" + classify + "-->" + longitude + "-->" + latitude + "-->"
						+ province + "-->" + city + "-->" + arer + "-->" + street);
				SellerInfo sellerInfo = new SellerInfo(seller_id, user.getU_id(), name, phone, province, city, arer,
						street, latitude, longitude, 0, classify);
				if (sellerDao.updateSellerInfo(sellerInfo)) {
					state = 200;
					result = "修改成功！";
				} else {
					state = 500;
					result = "服务器错误，稍后重试！";
				}
			} else {
				state = 405;// 无权访问
				result = "无权限请求数据";
			}
			jsonObject.put("state", state);
			jsonObject.put("result", result);
			pw.write(jsonObject.toString());
			System.out.println(jsonObject);
			pw.close();

		}
	}}