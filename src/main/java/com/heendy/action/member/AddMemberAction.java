package com.heendy.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.dao.MemberDAO;
import com.heendy.dto.MemberDTO;

public class AddMemberAction implements Action{
	private final MemberDAO memberDAO = MemberDAO.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("회원을 추가합니다.");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		int role_id = Integer.parseInt(request.getParameter("role"));
		MemberDTO memberVO = new MemberDTO(name, pwd, email, address, role_id);
		memberDAO.addMember(memberVO);
		response.sendRedirect("/HeendyFoody/pages/login/memberLogin.jsp");
//		nextPage = "/pages/login/memberLogin.jsp";
	}

}
