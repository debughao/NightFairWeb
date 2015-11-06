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
import com.nightfair.dao.OrderDao;
import com.nightfair.uitl.Datetime;
import com.nightfair.vo.Order;
import com.nightfair.vo.User;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetOrder
 */
@WebServlet("/buyer/getorder")
public class GetOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetOrder() {
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
				OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
				String action = request.getParameter("action");// 测试修改
				// String action="update";
				boolean issuccess = false;
				System.out.println("对账户的操作：" + action);
				if ("submit".equals(action)) {
					String seller_id = request.getParameter("seller_id");
					String coupon_id = request.getParameter("coupon_id");
					String amount = request.getParameter("money");
					String num = request.getParameter("num");
					String order_time = Datetime.getNow();
					int sellerid = Integer.parseInt(seller_id);
					int couponid = Integer.parseInt(coupon_id);
					double amounts = Double.parseDouble(amount);
					int order_id = orderDao.selectNonPaymentOrderid(user_id, couponid);
					System.out.println(order_id);
					Order order = new Order(order_id, sellerid, user_id, couponid, order_time, num, amounts, 0);
					System.out.println("提交订单信息 " + order);
					order_id = orderDao.insertOrder(order);
					if (order_id != 0) {
						status = 200;
						result = "提交订单成功！";
						jsonObject.put("order_id", order_id);
					} else {
						status = 501;
						result = "提交订单失败！";
					}
				} else if ("pay".equals(action)) {
					String orderid = request.getParameter("orderid");
					String money = request.getParameter("money");
					System.out.println("支付订单号-----" + orderid + "金额-----" + money);
					if (orderid != null && !orderid.equals("")) {
						issuccess = orderDao.updateOrder(Integer.parseInt(orderid), "pay");
						double money1;
						if (money.equals("")) {
							money1=0;
						}else {
							 money1 = Double.parseDouble(money);	
						}						
						if (money1 != 0) {
							AccountDao accountDao = DaoFactory.getInstance().getAccountDao();
							issuccess = accountDao.updateAccountbalance(user_id, "sub", money1);
						}
						if (issuccess) {
							status = 200;
							result = "支付成功！";
						}
					}else {
						if (issuccess) {
							status = 401;
							result = "订单号为空！";
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
