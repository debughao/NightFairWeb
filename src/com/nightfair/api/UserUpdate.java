package com.nightfair.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nightfair.dao.BuyerDao;
import com.nightfair.dao.DaoFactory;
import com.nightfair.uitl.Datetime;
import com.nightfair.uitl.MD5Util;
import com.nightfair.vo.BuyerInfo;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

@WebServlet("/user/update")
public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpdate() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		String key = request.getParameter("key");
		System.out.println(key);
		int status = 500;// 默认设置为成功
		String result = "无权访问";
		JSONObject jsonObject = new JSONObject();
		if ("a2e22c742b952403".equals(key)) {
			String action = request.getParameter("action");
			String user_id = request.getParameter("user_id");
			BuyerDao buyerDao = DaoFactory.getInstance().getBuyerDao();
			if ("get".equals(action)) {
				BuyerInfo buyerInfo = new BuyerInfo();
				buyerInfo = buyerDao.getBuyerinfo(Integer.parseInt(user_id));
				if (buyerInfo != null) {
					jsonObject.put("info", buyerInfo);
					status = 200;
					result = "获取数据成功";
				}
			} else if ("headface".equals(action)) {
				String picStr = request.getParameter("picStr");
				OutputStream out = null;
				if (picStr != null) {
					try {
						// Base64解码
						byte[] b = new BASE64Decoder().decodeBuffer(picStr);
						for (int i = 0; i < b.length; ++i) {
							if (b[i] < 0) {// 调整异常数据
								b[i] += 256;
							}
						}
						// 生成图片
						String picName = MD5Util.MD5(user_id + Datetime.getNow()) + ".png";
						String savePath = "C:\\NightFair\\image\\buyerhd\\" + user_id + "\\";
						File file = new File(savePath);
						if (!file.isDirectory()) {
							file.mkdirs();
						}
						String fileRealPath = savePath + picName;
						String imageUrl = "www.debughao.cn/image/buyerhd/" + user_id + "/" + picName;
						if (buyerDao.updateBuyerHd(imageUrl, Integer.parseInt(user_id))) {

							System.out.println(imageUrl);
							status = 200;
							result = "头像上传成功";
							out = new FileOutputStream(fileRealPath);
							out.write(b);
							out.flush();
						}
					} catch (Exception e) {
						result = "头像上传失败";
					} finally {
						out.close();
					}
				} else {
					result = "头像上传失败";
				}
			} else if ("info".equals(action)) {
				String nickname = request.getParameter("nickname");
				String sex = request.getParameter("sex");
				String age = request.getParameter("age");
				String star = request.getParameter("star");
				String hometown = request.getParameter("hometown");
				String address = request.getParameter("address");
				String autograph = request.getParameter("autograph");
				BuyerInfo buyerInfo = new BuyerInfo(user_id, nickname, sex, age, star, hometown, address, autograph,
						null);
				if (buyerDao.updateBuyerInfo(buyerInfo)) {
					status = 200;
					result = "更新个人资料成功";
				}
			} else {
				status = 404;
				result = "操作错误！";
			}
		}
		jsonObject.put("status", status);
		jsonObject.put("result", result);
		PrintWriter pw = response.getWriter();
		System.out.println(jsonObject);
		pw.write(jsonObject.toString());
		pw.close();
	}

}
