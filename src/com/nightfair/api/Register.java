package com.nightfair.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nightfair.dao.AccountDao;
import com.nightfair.dao.BuyerDao;
import com.nightfair.dao.DaoFactory;
import com.nightfair.dao.UserDao;
import com.nightfair.vo.BuyerInfo;
import com.nightfair.vo.User;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class Register
 */
@WebServlet("/buyer/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		int status = 404;
		String result = "";
		JSONObject jsonObject = new JSONObject();
		String key = request.getParameter("key");
		System.out.println("用户的key：" + key);
		if ("a2e22c742b952403".equals(key)) {
			String action = request.getParameter("action");
			System.out.println("用户操作"+action);
			UserDao userDao = DaoFactory.getInstance().getUserDao();
			if ("isAlready".equals(action)) {
				String phone = request.getParameter("phone");
				
				if (userDao.existUserByPhone(phone,"2")) {
					status=200;
					result="该手机号可以注册";
				}else{
					status=10002;
					result="该手机号已被注册";
				}
			}else if ("register".equals(action)) {
				String phone = request.getParameter("phone");
				String password = request.getParameter("password");
				String nickname = request.getParameter("nickname");			
				System.out.println(phone+"--->"+password+"---"+nickname);
				User user=new User(0, null, null, phone, password, "2");
				int user_id=userDao.phoneRegiserUser(user);
				if (user_id>0) {			
					BuyerDao buyerDao=DaoFactory.getInstance().getBuyerDao();
					BuyerInfo buyerInfo=new BuyerInfo(String.valueOf(user_id), nickname);
					AccountDao accountDao=DaoFactory.getInstance().getAccountDao();
					accountDao.insertAccount(user_id);
					buyerDao.insertBuyerInfo(buyerInfo);
					status=200;
					result="注册成功";
				}
			}
		} else {
			status = 10001;
			result = "错误的请求key！";
		}
		jsonObject.put("status", status);
		jsonObject.put("result", result);
		PrintWriter pw = response.getWriter();
		pw.write(jsonObject.toString());
		System.out.println(jsonObject);
		pw.close();
	}

}
