package com.bit2016.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.dao.BoardDao;
import com.bit2016.mysite.vo.BoardVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class ViewAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long no = Long.parseLong(request.getParameter("no"));
		
		BoardDao dao = new BoardDao();
		
		BoardVo list = dao.viewPost(no);
		
		request.setAttribute("list", list);
		request.setAttribute("no", no);
		
		WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
	}
}
