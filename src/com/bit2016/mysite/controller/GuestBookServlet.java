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
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
