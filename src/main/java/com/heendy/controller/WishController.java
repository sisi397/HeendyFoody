package com.heendy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;
import com.heendy.action.wish.WishActionFactory;

/**
 * @author 김시은
 * 
 * 좋아요 관련 Controller
 * 
 * */
@WebServlet("/wish/*")
public class WishController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 
	private final ActionFactory actionFactory = WishActionFactory.getInstance();
	 
	@Override
	protected void doGet(HttpServletRequest request,
	   HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
	   HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	  
	protected void doHandle(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String command = request.getPathInfo();
		
		Action action = this.actionFactory.getAction(command);
				    
		if (action != null) {
			action.execute(request, response);
		}
	}
	  
}
