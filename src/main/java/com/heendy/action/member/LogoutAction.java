package com.heendy.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.heendy.action.Action;
import com.heendy.dao.MemberDAO;

/**
 * 
 * @author 문석호, 이지민
 *	로그아웃시 세션 초기화 + 최근 본 상품 쿠키 삭제
 */
public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url;
		//쿠키를 처음 생성할 때 설정해줬던 값들 재설정 필요
		Cookie ck = new Cookie("RECENT_VIEW_ITEMS", null);
		ck.setMaxAge(0); //해당 쿠키를 없앤다.
		ck.setPath("/");
		response.addCookie(ck);

		//현재 세션이 존재하면 HttpSession을 반환하고 아니라면 null을 반환한다.
		HttpSession session=request.getSession(false);
		
		//session이 null이 아닌 경우(로그인한 세션을 가지고 있음)
		if(session!=null){
			//세션 값을 초기화한다.
			session.invalidate();
		}    
		//로그아웃 성공 시 이동할 페이지 지정
		url = request.getContextPath() + "/main";	
		response.sendRedirect(url);
	}
}