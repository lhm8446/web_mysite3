package com.bit2016.mysite.action.guestbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.dao.GuestBookDao;
import com.bit2016.mysite.vo.GuestBookVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

import net.sf.json.JSONObject;

public class AjaxAddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GuestBookDao dao = new GuestBookDao();
		GuestBookVo vo = new GuestBookVo();
		String name = request.getParameter("name");
		String password = request.getParameter("pass");
		String content = request.getParameter("content");

		vo.setName(name);
		vo.setPassword(password);
		vo.setContent(content);
		
		Long result = dao.insert(vo);
		
		vo = dao.get(result);
		
		Map<String ,Object> map = new HashMap<String, Object>();
		
		map.put("result", "success");
		map.put("data", vo);
		
		response.setContentType("application/json; charset=utf-8");
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().println(jsonObject.toString());
		

	}
}
