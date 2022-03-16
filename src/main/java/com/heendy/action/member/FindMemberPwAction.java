package com.heendy.action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.dao.MemberDAO;

/**
 * 
 * @author 문석호
 * 회원 비밀번호 찾기 기능 Action 클래스
 */
public class FindMemberPwAction implements Action{
	private final MemberDAO memberDAO = MemberDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/pages/login/findPw.jsp";
		String member_name = request.getParameter("name");
		String member_email = request.getParameter("email");
		String userPassword = memberDAO.findMemberPassword(member_name, member_email);
		
		request.setAttribute("userPw", userPassword);
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}

}
