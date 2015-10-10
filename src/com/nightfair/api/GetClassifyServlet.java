package com.nightfair.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nightfair.dao.ClassifyDao;
import com.nightfair.dao.DaoFactory;
import com.nightfair.vo.Classify;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: GetClassifyServlet
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author debughao
 * @date 2015年9月16日
 */
@WebServlet("/apis/classify/get")
public class GetClassifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetClassifyServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("Utf-8");
		response.setCharacterEncoding("UTF-8");// 设置将字符以"UTF-8"编码输出到客户端浏览器
		// 通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
		response.setHeader("content-type", "text/html;charset=UTF-8");
		int state = 0;// 默认设置为成功
		PrintWriter pw = response.getWriter();
		ClassifyDao classifyDao = DaoFactory.getInstance().getClassifyDao();
		ArrayList<Classify> classifies = new ArrayList<Classify>();
		classifies = classifyDao.getAllClassify();
		JSONArray jsonArray = JSONArray.fromObject(classifies);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("state", state);
		jsonObject.put("data", jsonArray);
		pw.write(jsonObject.toString());
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
