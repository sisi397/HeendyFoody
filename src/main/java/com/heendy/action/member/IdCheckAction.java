package com.heendy.action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.dao.MemberDAO;

public class IdCheckAction implements Action{
	private final MemberDAO memberDAO = MemberDAO.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/pages/login/idCheck.jsp";
		
		String name = request.getParameter("name").trim(); //이름 끝에 공백이 있다면 제거
		int result = memberDAO.duplicateId(name);	//이름 중복 검사(1이면 가능 0이면 불가능)
		request.setAttribute("result", result);	//결과 attribute에 담기
		request.setAttribute("id", name);	//결과 attribute에 담기
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}

}
