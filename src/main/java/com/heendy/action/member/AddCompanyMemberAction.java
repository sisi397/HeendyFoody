package com.heendy.action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.dao.CompanyMemberDAO;
import com.heendy.dto.CompanyMemberDTO;

/**
 * 
 * @author 문석호
 * 업체 회원 회원가입 Action 클래스
 */
public class AddCompanyMemberAction implements Action{
	private final CompanyMemberDAO companyMemberDAO = CompanyMemberDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/member/memberLogin.do";
		//회원가입jsp 페이지에서 파라미터 받아오기
		String company_name = request.getParameter("company_name");
		String company_pwd = request.getParameter("company_pwd");
		String company_tel = request.getParameter("company_tel");
		String company_email = request.getParameter("company_email");
		int role_id = Integer.parseInt(request.getParameter("role"));
		
		//company 멤버 DTO에 불러온 값 넣기
		CompanyMemberDTO cmemberVO = new CompanyMemberDTO(company_name, company_pwd, company_tel, company_email, role_id);
		companyMemberDAO.addCompanyMember(cmemberVO);
		System.out.println("업체회원이 추가 되었습니다.");
		
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);	// 회원 가입 후 로그인 페이지로 dispatcher
	    dispatcher.forward(request, response);
	}
	

}
