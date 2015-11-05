package com.nightfair.api;

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
import com.nightfair.vo.SellerCommentsBuyer;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetComments
 */
@WebServlet("/buyer/getcomments")
public class GetComments extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetComments() {
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
		String key = request.getParameter("key");
		String pageNum = request.getParameter("pageNum");
		String pageNow = request.getParameter("pageNow");
		String seller_id = request.getParameter("seller_id");
		System.out.println("商家id" + seller_id+"key值"+key);
		SellerCommentsBuyer sellerComment = null;
		if ("a2e22c742b952403".equals(key)) {
			CommentDao commentDao = DaoFactory.getInstance().getCommentDao();
			System.out.println("每页码条数:" + pageNum + "当前页" + pageNow);
			if (!"".equals(seller_id) && null != seller_id) {
				int sellerid=Integer.parseInt(seller_id);
				ArrayList<Integer> num = commentDao.getCommentNumSellerid(sellerid, Integer.parseInt(pageNum));
				sellerComment = commentDao.getSellerCommentsBuyerBySellerid(sellerid, Integer.parseInt(pageNum),
						Integer.parseInt(pageNow));
				if (sellerComment != null) {
					jsonObject.put("data", sellerComment);
					jsonObject.put("total", num.get(0));
					jsonObject.put("totalpage", num.get(1));
					status = 200;
					result = "请求数据成功！";
				}else {
					status=0;
					result = "该商家暂无收到评论！";
				}				
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
