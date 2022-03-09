package com.heendy.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;
import com.heendy.dao.MemberDAO;
import com.heendy.dto.MemberDTO;

public class LoginMemberAction implements Action{
	private final MemberDAO memberDAO = MemberDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		String member_name = request.getParameter("id");
		String member_password = request.getParameter("pwd");
		MemberDTO memberVO = new MemberDTO();
		memberVO.setMemberName(member_name);
		memberVO.setMemberPassword(member_password);
		boolean result = memberDAO.isExisted(memberVO);
		System.out.println(result);
		if(result) {	//로그인 성공시
			System.out.println("로그인 성공!");
			HttpSession session = request.getSession();
			session.setAttribute("isLogin", true); //isLogin 속성을 true로 저장
			session.setAttribute("loginUser", memberVO);
			url="/member/index.do";
			request.getRequestDispatcher(url).forward(request, response);
		}else {		//로그인 실패시
			HttpSession session = request.getSession();
			session.setAttribute("isLogin", false); //isLogin 속성을 false로 저장
		}
	}

	
}
