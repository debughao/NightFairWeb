package com.nightfair.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nightfair.dao.DaoFactory;
import com.nightfair.dao.GoodDao;
import com.nightfair.dao.SellerDao;
import com.nightfair.vo.Goods;
import com.nightfair.vo.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * GetGoodsBySellerid
 * 
 * 2015年9月11日 上午10:55:14
 * 
 * @version 1.0.0
 *
 */
@WebServlet("/Seller/GetGoods")
public class GetGoodsBySellerid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetGoodsBySellerid() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		JSONObject jsonObject = new JSONObject();
		int status = 404;
		String result_status = "";
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			SellerDao sellerDao = DaoFactory.getInstance().getSellerDao();
			GoodDao goodDao = DaoFactory.getInstance().getGoodDao();
			int seller_id = sellerDao.getSelleridByUid(user.getU_id());
			String action = request.getParameter("action");
			System.out.println("操作:" + action);			
			if ("select".equals(action)) {
				ArrayList<Goods> goodsList = new ArrayList<Goods>();
				goodsList = goodDao.getGoodsBysellerid(seller_id);
				System.out.println(goodsList);
				JSONArray jsonArray = JSONArray.fromObject(goodsList);
				jsonObject.put("data", jsonArray);
				status = 200;
				result_status = "请求数据成功！";
			} else if ("add".equals(action)) {
				String good_name = request.getParameter("good_name");
				double real_price = Double.parseDouble(request.getParameter("real_price"));
				int seller_counts = Integer.parseInt(request.getParameter("seller_counts"));
				String introduction = request.getParameter("introduction");
				System.out.println(seller_id + "-->" + good_name + "-->" + real_price + "-->" + seller_counts + "-->"
						+ "introduction");
				Goods goods = goodDao
						.addGoods(new Goods(seller_id, good_name, real_price, seller_counts, introduction));
				jsonObject.put("goods", JSONObject.fromObject(goods));
				status = 200;
				result_status = "请求数据成功！";
			} else if ("edit".equals(action)) {
				int id = Integer.parseInt(request.getParameter("id"));
				Goods goods = goodDao.getGoodsbyid(id);
				jsonObject.put("goods", JSONObject.fromObject(goods));
				status = 200;
			} else if ("update".equals(action)) {
				int id = Integer.parseInt(request.getParameter("id"));
				String good_name = request.getParameter("good_name");
				double real_price = Double.parseDouble(request.getParameter("real_price"));
				int seller_counts = Integer.parseInt(request.getParameter("seller_counts"));
				String introduction = request.getParameter("introduction");
				Goods goods = new Goods(id, good_name, real_price, seller_counts, introduction, seller_id);
				if (goodDao.updateGoods(goods)) {
					status = 200;
					result_status = "请求数据成功！";
				} else {
					status = 500;
					result_status = "服务器内部错误！";
				}
			} else if ("delete".equals(action)) {
				int id = Integer.parseInt(request.getParameter("id"));
				if (goodDao.deleteGoods(id)) {
					status = 200;
					result_status = "请求数据成功！";
				} else {
					status = 500;
					result_status = "服务器内部错误！";
				}

			}
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
