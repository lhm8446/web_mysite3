package com.bit2016.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class DeleteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// String no = request.getParameter("no");
		// request.setAttribute("no", no);
		// forwarding(request 연장, request dispatch)
		
		WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
	}
}