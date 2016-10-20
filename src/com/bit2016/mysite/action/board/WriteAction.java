package com.bit2016.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2016.mysite.dao.BoardDao;
import com.bit2016.mysite.vo.BoardVo;
import com.bit2016.mysite.vo.UserVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//세션 받아오기
		HttpSession session = request.getSession();
		
		if( session == null ) {
			WebUtil.redirect(request, response, "/mysite3/board" );
			return;
		}	
		//현재 유저 세션 받아오기
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		
		if( authUser == null ) {
			WebUtil.redirect(request, response, "/mysite3/board" );
			return;
		}
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		BoardVo vo = new BoardVo();
		
		vo.setTitle(title);
		vo.setContent(content);
		
		BoardDao dao = new BoardDao();
		
		//새로운 게시판 작성
		dao.newWrite(vo, authUser);
		
		
		WebUtil.redirect(request, response,"/mysite3/board" );
	}
}
