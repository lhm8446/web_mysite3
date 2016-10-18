package com.bit2016.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.dao.GuestBookDao;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GuestBookDao dao = new GuestBookDao();
		
		Long no = Long.parseLong(request.getParameter("no"));
		String password = request.getParameter("password");
		dao.delete(no, password);

		WebUtil.redirect(request, response, "/mysite3/guestbook");

	}

}