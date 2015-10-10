package com.nightfair.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nightfair.dao.DaoFactory;
import com.nightfair.dao.UserDao;
import com.nightfair.vo.User;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Buyer/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		int status = 404;
		String errReason="";
		JSONObject jsonObject = new JSONObject();
		UserDao userDao = DaoFactory.getInstance().getUserDao();
		String key =request.getParameter("key");
		System.out.println("用户的key："+key);
		if ("123456".equals(key)) {
			String username= request.getParameter("userName");		
			String password= request.getParameter("userpassword");
			System.out.println("用户名："+username+"密码："+password);
			User user2=userDao.isPassLogin(username ,password,"2");
			if(user2==null){
				  status=10002;// 10002表示不可以登录
				  errReason="登录名或密码错误！";
			}else{
				System.out.println(user2);
				status=200;
				errReason="登录成功！";
			}
			
		}else{
			status=10001;
			errReason="错误的请求key！";
		}
		jsonObject.put("status", status);
		jsonObject.put("errReason", errReason);
		PrintWriter pw = response.getWriter();
		pw.write(jsonObject.toString());
		System.out.println(jsonObject);
		pw.close();
	}

}
