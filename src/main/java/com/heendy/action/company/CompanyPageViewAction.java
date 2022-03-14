package com.heendy.action.company;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;

/**
 * @author 김시은
 * 업체로 로그인 시 페이지 이동하는 Action 클래스
 * 
 */
public class CompanyPageViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/pages/company/company.jsp";
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}

}
