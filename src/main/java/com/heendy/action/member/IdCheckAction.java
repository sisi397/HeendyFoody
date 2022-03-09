package com.heendy.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.dao.MemberDAO;

public class IdCheckAction implements Action{
	private final MemberDAO memberDAO = MemberDAO.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		int result = memberDAO.duplicateId(name);
		
	}

}
