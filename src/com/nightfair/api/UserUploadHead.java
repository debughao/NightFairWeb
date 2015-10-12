package com.nightfair.api;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;


@WebServlet("/user/uploadhd")
public class UserUploadHead extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUploadHead() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		String picStr = request.getParameter("picStr");
		JSONObject jsonObject = new JSONObject();
		int state = 0;// 默认设置为成功
		String result = "success";
		OutputStream out = null;
		if (picStr != null){
			try {
				// Base64解码
				byte[] b = new BASE64Decoder().decodeBuffer(picStr);
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {// 调整异常数据
						b[i] += 256;
					}
				}
				// 生成jpeg图片
				String imgFilePath = "D://"+"123.png";// 新生成的图片
				out = new FileOutputStream(imgFilePath);
				out.write(b);
				out.flush();
			} catch (Exception e) {
				result = "fail";
			}finally{
				out.close();
			}
		}else{
			result = "fail";
		}
		PrintWriter pw = response.getWriter();
		System.out.println(result);
		pw.write(result);
		pw.close();
	}

}
