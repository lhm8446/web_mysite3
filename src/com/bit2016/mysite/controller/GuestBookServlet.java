package com.bit2016.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.action.guestbook.GuestBookActionFactory;
import com.bit2016.web.Action;
import com.bit2016.web.ActionFactory;


@WebServlet("/guestbook")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//action name 가져오기
		
		String actionName = request.getParameter("a");
		
		ActionFactory af = new GuestBookActionFactory();
		Action action = af.getAction(actionName);
		action.execute(request, response);
		
//		if("add".equals(actionName)){
//			//add 요청 처리
//			
//			String name = request.getParameter("name");
//			String password = request.getParameter("pass");
//			String content = request.getParameter("content");
//
//			GuestBookVo vo = new GuestBookVo();
//			vo.setName(name);
//			vo.setContent(content);
//			vo.setPassword(password);
//
//			GuestBookDao dao = new GuestBookDao();
//			dao.insert(vo);
//			//redirect
//
//			response.sendRedirect("/guestbook02/gu");
//			
//		}else if("delete".equals(actionName)){
//			//delete
//			
//			Long no = Long.parseLong(request.getParameter("no")) ;
//			String password = request.getParameter("password");
//
//			GuestBookDao dao = new GuestBookDao();
//
//			dao.delete(no,password);
//
//			//redirect
//			response.sendRedirect("/guestbook02/gu");
//			
//		}else if("deleteform".equals(actionName)){
//			
//			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
//			rd.forward(request, response);
//			
//		}else{
//			//default action 처리 ( 리스트 처리 )
//			
//			// 객체생성
//			GuestBookDao dao = new GuestBookDao();
//			List<GuestBookVo> list = dao.getList();
//			
//			//request 범위에 모델 데이터 저장  (요청받은 request 안에다가 리스트 데이터 저장)
//			//request forwarding 전에 저장해줘야함
//			request.setAttribute("list", list);
//			
//			//forwarding(request 연장, request dispatch)
//			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
//			rd.forward(request, response);
//		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
