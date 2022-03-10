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
		if(result) {	//로그인 성공시
			System.out.println("로그인 성공!");
			memberVO = memberDAO.getMember(member_name); //성공했으면 멤버를 조회해서 속성들을 가져온다
			
			HttpSession session = request.getSession();
			session.setAttribute("isLogin", true); //isLogin 속성을 true로 저장
			session.setAttribute("loginUser", memberVO);
			url="/member/index.do";
			request.getRequestDispatcher(url).forward(request, response);
		}else {		//로그인 실패시
			System.out.println("로그인 실패");
			url="/pages/login/loginFail.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
}
