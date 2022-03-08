package com.heendy.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;
import com.heendy.dao.MemberDAO;
import com.heendy.dto.MemberDTO;

public class LoginMemberAction implements Action{
	private final MemberDAO memberDAO = MemberDAO.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String member_name = request.getParameter("id");
		String member_password = request.getParameter("pwd");
		MemberDTO memberVO = new MemberDTO();
		memberVO.setMemberName(member_name);
		memberVO.setMemberPassword(member_password);
		boolean result = memberDAO.isExisted(memberVO);
		System.out.println(result);
		if(result) {
			
		}
	}

	
}
