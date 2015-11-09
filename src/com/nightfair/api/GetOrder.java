package com.nightfair.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.nightfair.dao.AccountDao;
import com.nightfair.dao.BuyerDao;
import com.nightfair.dao.CommentDao;
import com.nightfair.dao.DaoFactory;
import com.nightfair.dao.OrderDao;
import com.nightfair.uitl.Datetime;
import com.nightfair.vo.Buyer_order;
import com.nightfair.vo.Comment;
import com.nightfair.vo.Order;
import com.nightfair.vo.User;
import net.sf.json.JSONArray;
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
				// int user_id = 201547;
				OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
				String action = request.getParameter("action");
				// String action="select";// 测试修改
				boolean issuccess = false;
				System.out.println("对账户的操作：" + action);
				if ("submit".equals(action)) {
					String seller_id = request.getParameter("seller_id");
					String coupon_id = request.getParameter("coupon_id");
					String amount = request.getParameter("money");
					String num = request.getParameter("num");
					String order_time = Datetime.getNow();
					System.out
							.println(seller_id + "---" + coupon_id + "---" + amount + "---" + num + "---" + order_time);
					int sellerid = Integer.parseInt(seller_id);
					int couponid = Integer.parseInt(coupon_id);
					double amounts = Double.parseDouble(amount);
					int order_id = orderDao.selectNonPaymentOrderid(user_id, couponid);
					System.out.println(order_id);
					Order order = new Order(order_id, sellerid, user_id, couponid, order_time, num, amounts, 0, null,0);
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
							money1 = 0;
						} else {
							money1 = Double.parseDouble(money);
						}
						if (money1 != 0) {
							issuccess = orderDao.createCouponcode(Integer.parseInt(orderid));
							AccountDao accountDao = DaoFactory.getInstance().getAccountDao();
							issuccess = accountDao.updateAccountbalance(user_id, "sub", money1);
						}
						if (issuccess) {
							status = 200;
							result = "支付成功！";
						}
					} else {
						status = 401;
						result = "订单号为空！";

					}
				} else if ("select".equals(action)) {
					String state = request.getParameter("state");
					String type = request.getParameter("type");				
					ArrayList<Buyer_order> buyer_orders = new ArrayList<Buyer_order>();
					buyer_orders = orderDao.selectOrders(user_id, state,type);
					if (buyer_orders.size() > 0) {
						JSONArray jsonArray = JSONArray.fromObject(buyer_orders);
						jsonObject.put("data", jsonArray);
						status = 200;
						result = "查询成功！";
					} else {
						status = 201;
						result = "查询订单为空！";
					}
					
				}				
				else if ("cancal".equals(action)) {
					String order_id = request.getParameter("orderid");
					int ordeid = Integer.parseInt(order_id);
					boolean isSuccess = orderDao.cancalOrder(ordeid);
					if (isSuccess) {
						status = 200;
						result = "取消订单成功！";
					} else {
						status = 201;
						result = "取消订单失败！";
					}
				} else if ("refues".equals(action)) {
					String order_id = request.getParameter("orderid");
					int ordeid = Integer.parseInt(order_id);
					System.out.println("退款订单号：" + order_id);
					issuccess = orderDao.updateOrder(ordeid, "refues");
					if (issuccess) {
						status = 200;
						result = "已接受退款请求！";
					} else {
						status = 201;
						result = "请求退款失败，请稍后重试！";
					}
				}
				else if ("comment".equals(action)) {
					String order_id = request.getParameter("orderid");
					String grades= request.getParameter("grades");
					String sellerid= request.getParameter("sellerid");
					String comments= request.getParameter("comment");
					String time= Datetime.getNow();
					int ordeid = Integer.parseInt(order_id);
					System.out.println("订单号：" + order_id+"grades"+grades+"sellerid"+sellerid+"comments"+comments);
					issuccess = orderDao.updateOrder(ordeid, "comment");
					CommentDao commentDao =DaoFactory.getInstance().getCommentDao();
					BuyerDao buyerDao =DaoFactory.getInstance().getBuyerDao();
					int buyerid=buyerDao.getBuyerinfo(user_id).getId();
					double grade=Double.parseDouble(grades);		
					int seller_id=Integer.parseInt(sellerid);
					Comment comment=new Comment(0, grade, comments, time, seller_id, buyerid);
					issuccess=commentDao.insertCommnet(comment);
					if (issuccess) {
						status = 200;
						result = "评论成功！";
					} else {
						status = 404;
						result = "评论失败！";
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
