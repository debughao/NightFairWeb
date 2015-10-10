package com.nightfair.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.nightfair.dao.DaoFactory;
import com.nightfair.dao.UserDao;
import com.nightfair.uitl.MD5Util;
import com.nightfair.vo.User;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: Registers
 * @Description: TODO(注册异步提交)
 * @author debughao
 * @date 2015年9月17日
 */
@WebServlet("/Register")
public class Registers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registers() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		JSONObject jsonObject = new JSONObject();
		int status = 200;
		boolean values = true;
		String validate = request.getParameter("validate");
		String parameter = request.getParameter("parameter");
		System.out.println("操作:" + validate + "输入内容：" + parameter);
		UserDao userDao = DaoFactory.getInstance().getUserDao();
		if ("username".equals(validate)) {
			if (!userDao.existUserByUsername(parameter)) {
				status = 404;// 404表示不可以注册
				values = false;
			}
		} else if ("email".equals(validate)) {
			if (!userDao.existUserByEmail(parameter)) {
				status = 404;// 404表示不可以注册
				values = false;
			}
		} else if ("phone".equals(validate)) {
			if (!userDao.existUserByPhone(parameter)) {
				status = 404;// 404表示不可以注册
				values = false;
			}
		} else if ("register".equals(validate)) {
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String password = request.getParameter("password");
			User user = new User(0, null, username, email, phone, password, 2);
			if (userDao.regiserUser(user)) {
				status = 404;// 404表示不可以注册
				values = false;
			}
		} else if ("login".equals(validate)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user2 = userDao.isPassLogin(username, MD5Util.MD5(password), "1");
			if (user2 == null) {
				status = 404;// 404表示不可以登录
				values = false;
			} else {
				System.out.println(user2);
				request.getSession().setAttribute("user", user2);
			}
		}
		if ("logout".equals(validate)) {
			request.getSession().invalidate();
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		}
		jsonObject.put("status", status);
		jsonObject.put("values", values);
		PrintWriter pw = response.getWriter();
		pw.write(jsonObject.toString());
		System.out.println(jsonObject);
		pw.close();
	}

}
