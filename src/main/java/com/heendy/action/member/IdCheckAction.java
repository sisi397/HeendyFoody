package com.heendy.action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.dao.CompanyMemberDAO;
import com.heendy.dao.MemberDAO;

/**
 * 
 * @author 문석호
 * 회원 가입시 ID 중복 체크를 위한 Action클래스
 */
public class IdCheckAction implements Action{
	private final MemberDAO memberDAO = MemberDAO.getInstance();
	private final CompanyMemberDAO cmemberDAO = CompanyMemberDAO.getInstance();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/pages/login/idCheck.jsp";
		int role = Integer.parseInt(request.getParameter("role_id")); //권한 가져오기(1이면 업체회원 0이면 일반회원)
		String name = request.getParameter("name").trim(); //이름 끝에 공백이 있다면 제거
		int result = -1;
		if(role == 1) {	//업체 회원인 경우
			result = cmemberDAO.duplicateCompanyId(name); //업체이름 중복 검사(0이면 가능 1이면 불가능)
		}else {	//일반 회원인 경우
			result = memberDAO.duplicateId(name);	//멤버이름 중복 검사(0이면 가능 1이면 불가능)
		}
		request.setAttribute("result", result);	//결과 attribute에 담기
		request.setAttribute("id", name);	// attribute에 담기
		request.setAttribute("role", role);	// attribute에 담기
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}

}
