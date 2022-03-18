package com.heendy.action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.dao.MemberDAO;
import com.heendy.dto.MemberDTO;

/**
 * 
 * @author 문석호
 *	일반 회원 회원 가입 Action 클래스
 */
public class AddMemberAction implements Action{
	private final MemberDAO memberDAO = MemberDAO.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//이동할 url 설정
		String url = "/member/memberLogin.do";	
		
		//필요한 파라미터 불러오기
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		int role_id = Integer.parseInt(request.getParameter("role"));
		String birthDate = request.getParameter("birthdate");
		
		MemberDTO memberVO = new MemberDTO(name, pwd, email, address, role_id, birthDate);
		// 회원 추가하기
		memberDAO.addMember(memberVO);
		System.out.println("회원이 추가 되었습니다.");
		
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}

}
