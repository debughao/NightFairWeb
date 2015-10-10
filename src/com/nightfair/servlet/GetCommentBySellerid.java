package com.nightfair.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.nightfair.dao.CommentDao;
import com.nightfair.dao.DaoFactory;
import com.nightfair.dao.SellerDao;
import com.nightfair.vo.Comment;
import com.nightfair.vo.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: GetCommentBySellerid
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author debughao
 * @date 2015年9月16日
 */
@WebServlet("/Seller/GetComment")
public class GetCommentBySellerid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetCommentBySellerid() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		JSONObject jsonObject = new JSONObject();
		int status = 404;
		String result_status = "获取数据失败";
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			SellerDao sellerDao = DaoFactory.getInstance().getSellerDao();
			int seller_id = sellerDao.getSelleridByUid(user.getU_id());
			System.out.println("商家id"+seller_id);
			String action = request.getParameter("action");
			String pageNum = request.getParameter("pageNum");
			String pageNow = request.getParameter("pageNow");
			CommentDao commentDao = DaoFactory.getInstance().getCommentDao();
			System.out.println("操作:" + action);
			System.out.println("每页码:" + pageNum + "当前页" + pageNow);
			if ("getnum".equals(action)) {
				ArrayList<Integer> num = commentDao.getCommentNumSellerid(seller_id, Integer.parseInt(pageNum));
				ArrayList<Comment> commentsList = commentDao.getCommentBySellerid(seller_id, Integer.parseInt(pageNum),
						Integer.parseInt(pageNow));
				JSONArray jsonArray = JSONArray.fromObject(commentsList);
				jsonObject.put("data", jsonArray);
				jsonObject.put("commentsnum", num.get(0));
				jsonObject.put("pagenum", num.get(1));
				status = 200;
				result_status = "请求数据成功！";
			} else if ("select".equals(action)) {
				ArrayList<Comment> commentsList = commentDao.getCommentBySellerid(seller_id, Integer.parseInt(pageNum),
						Integer.parseInt(pageNow));
				JSONArray jsonArray = JSONArray.fromObject(commentsList);
				jsonObject.put("data", jsonArray);
				status = 200;
				result_status = "请求数据成功！";
			}
		} else {
			status = 405;// 无权访问
			result_status = "无权限请求数据";
		}
		jsonObject.put("status", status);
		jsonObject.put("result_status", result_status);
		PrintWriter pw = response.getWriter();
		System.out.println(jsonObject);
		pw.write(jsonObject.toString());
		pw.close();
	}

}
