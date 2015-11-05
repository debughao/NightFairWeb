package com.nightfair.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nightfair.dao.GoodDao;
import com.nightfair.vo.Goods;

/**
 * Servlet implementation class GetGoodsServlet
 */
@WebServlet("/GetGoodsServlet")
public class GetGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//如果服务器端想把客户端查询的结果返回给客户端，就需要通过printwriter.writer(String)写回给端端
		PrintWriter out=response.getWriter();
		List<Goods> list=new ArrayList<Goods>();
		GoodDao goodDao=new GoodDao();
		list=goodDao.getGoods();
		Gson gson=new Gson();
		String result=gson.toJson(list, new TypeToken<List<Goods>>(){}.getType());
		out.write(result);
		/*list=goodDao.getGoods();
		JSONArray jsonArray=JSONArray.fromObject(list);
		out.write(jsonArray.toString());*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
