package com.heendy.controller;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;
import com.heendy.action.member.MemberActionFactory;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
        super();
    }

	public void init() throws ServletException {
		System.out.println("멤버 컨트롤러 실행");
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//메서드팩토리 패턴 (액션세팅)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String command = request.getPathInfo();	//URL에서 요청명 가져오기
		
		ActionFactory af = MemberActionFactory.getInstance();
		Action action = af.getAction(command);
		
		if(action != null) {
		System.out.println(action + "을 실행합니다.");
		action.execute(request, response);
		}
	}
}
