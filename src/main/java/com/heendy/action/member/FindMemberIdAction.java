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
 * 회원 아이디 찾기 기능 Action 클래스
 */
public class FindMemberIdAction implements Action{
	private final MemberDAO memberDAO = MemberDAO.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/pages/login/findId.jsp";
		String member_email = request.getParameter("email");
		String userName = memberDAO.findMemberId(member_email);
		
		request.setAttribute("userName", userName);
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}

}
