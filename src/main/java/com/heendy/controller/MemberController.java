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

/**
 * @author 문석호
 * 멤버 관련 Controller
 */
@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
        super();
    }

	public void init() throws ServletException {
		System.out.println("member 컨트롤러 실행");
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		//URL에서 요청명 가져오기
		String command = request.getPathInfo();	
		
		//메서드팩토리 패턴 (액션세팅)
		ActionFactory af = MemberActionFactory.getInstance();
		Action action = af.getAction(command);
		
		if(action != null) {
		System.out.println(action + "을 실행합니다.");
		action.execute(request, response);
		}
	}
}
