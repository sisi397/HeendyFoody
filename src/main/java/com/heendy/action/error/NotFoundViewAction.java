package com.heendy.action.error;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;


/**
 * @author 이승준
 * 
 * 404 에러 페이지 view 반환 Action 클래스
 * */
public class NotFoundViewAction implements Action{

	private final String VIEW_URL = "/404error.jsp";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_URL);
		 
		 dispatcher.forward(request, response);
		
	}
	
	

}
