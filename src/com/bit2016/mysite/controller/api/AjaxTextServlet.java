package com.bit2016.mysite.controller.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.vo.UserVo;

import net.sf.json.JSONArray;

@WebServlet("/ajax")
public class AjaxTextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json; charset=utf-8");
		
		List<UserVo> list = new ArrayList<UserVo>();
		
		UserVo vo1 = new UserVo();
		
		vo1.setNo(10L);
		vo1.setName("이하민");
		vo1.setEmail("lhm8446@gmail.com");
		vo1.setGender("mail");
		
		list.add(vo1);
		
		UserVo vo2 = new UserVo();
		vo2.setNo(10L);
		vo2.setName("두울리");
		vo2.setEmail("doolu@gmail.com");
		vo2.setGender("mail");
		
		list.add(vo2);
		
		PrintWriter out = response.getWriter();
		
		JSONArray jsonArray = JSONArray.fromObject(list);

		out.println(jsonArray.toString());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
