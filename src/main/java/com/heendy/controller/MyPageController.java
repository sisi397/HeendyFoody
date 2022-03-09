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


@WebServlet("/mypage/*")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		//한국어 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
	    String command = request.getPathInfo();
	    System.out.println("MyPageController : " + command);
	    
	    ActionFactory af = MyPageActionFactory.getInstance();
	    Action action = af.getAction(command);

	    if (action != null) {
	      action.execute(request, response);
	    }
	}//end do handle
}
