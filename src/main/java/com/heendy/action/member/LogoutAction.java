package com.heendy.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.heendy.action.Action;
import com.heendy.dao.MemberDAO;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url;
		Cookie ck = new Cookie("RECENT_VIEW_ITEMS", null);
		ck.setMaxAge(0);
		ck.setPath("/");
		response.addCookie(ck);

		HttpSession session=request.getSession(false);
		if(session!=null){
			session.invalidate();
		}    
		
		url = request.getContextPath() + "/main";	//로그인 성공 시 이동할 페이지 지정
		response.sendRedirect(url);
	}
}