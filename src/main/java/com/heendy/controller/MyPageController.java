package com.heendy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;
import com.heendy.action.mypage.MyPageActionFactory;

/**
 * @author 이지민
 * 마이페이지 관련 Controller
 * */

@WebServlet("/mypage/*")
public class MyPageController extends HttpServlet {
	
	private final ActionFactory actionFactory = MyPageActionFactory.getInstance();
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}
	
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		
	    String command = request.getPathInfo();
	    System.out.println("MyPageController : " + command);
	    
	    
	    Action action = this.actionFactory.getAction(command);

	    if (action != null) {
	      action.execute(request, response);
	    }
	}
}
