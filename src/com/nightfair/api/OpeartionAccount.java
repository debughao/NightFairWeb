package com.nightfair.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nightfair.dao.AccountDao;
import com.nightfair.dao.DaoFactory;
import com.nightfair.uitl.Datetime;
import com.nightfair.vo.Recharge;
import com.nightfair.vo.User;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class OpeartionAccount
 */
@WebServlet("/buyer/opeartionaccount")
public class OpeartionAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OpeartionAccount() {
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
			if (user != null) {
				// if (user == null) {//测试修改
				int user_id = user.getU_id();// 测试修改
				// int user_id = 201502;
				AccountDao accountDao = DaoFactory.getInstance().getAccountDao();
				String action = request.getParameter("action");// 测试修改
				// String action="update";
				System.out.println("对账户的操作：" + action);
				if ("select".equals(action)) {
					double balance = accountDao.selectAccount(user_id);
					status = 200;
					result = "获取余额成功！";
					jsonObject.put("balance", balance);
				} else if ("add".equals(action)) {
					String money= request.getParameter("money");
					String recharge_way= request.getParameter("recharge_way");
					String transactionid= request.getParameter("transactionid");
					String datetime= Datetime.getNow();
					double accoumnt=Double.parseDouble(money);
					System.out.println("金额"+money+"----充值方式"+recharge_way+"----订单号"+transactionid+"---时间"+datetime);
					Recharge recharge =new Recharge(user_id, transactionid, recharge_way, accoumnt, datetime);
					// String money="200";//测试修改
					if (!money.equals("") && money != null) {
						boolean isSuccess = accountDao.updateAccountbalance(user_id, action, accoumnt);
						isSuccess=accountDao.insertRecharge(recharge);
						if (isSuccess) {
							status = 200;
							result = "充值成功！";
							double balance = accountDao.selectAccount(user_id);
							jsonObject.put("balance", balance);
						} else {
							status = 501;
							result = "充值失败！";
						}
					}
				} else if ("sub".equals(action)) {
					String money = request.getParameter("money");
					// String money="200";//测试修改
					if (!money.equals("") && money != null) {
						boolean isSuccess = accountDao.updateAccountbalance(user_id, action, Double.parseDouble(money));
						if (isSuccess) {
							status = 200;
							result = "消费扣除成功！";
							double balance = accountDao.selectAccount(user_id);
							jsonObject.put("balance", balance);
						} else {
							status = 501;
							result = "消费扣除失败！";
						}
					}
				} else if ("update".equals(action)) {
					String payword = request.getParameter("payword");
					// String payword="123456";//测试修改
					if (!payword.equals("") && payword != null) {
						boolean isSuccess = accountDao.updatPayword(user_id, payword);
						if (isSuccess) {
							status = 200;
							result = "修改密码成功！";
						} else {
							status = 501;
							result = "修改密码失败！";
						}
					}
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
