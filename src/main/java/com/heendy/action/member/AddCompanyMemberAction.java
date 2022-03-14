package com.heendy.action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.dao.CompanyMemberDAO;
import com.heendy.dto.CompanyMemberDTO;

public class AddCompanyMemberAction implements Action{
	private final CompanyMemberDAO companyMemberDAO = CompanyMemberDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/member/memberLogin.do";
		String company_name = request.getParameter("company_name");
		String company_pwd = request.getParameter("company_pwd");
		String company_tel = request.getParameter("company_tel");
		String company_email = request.getParameter("company_email");
		int role_id = Integer.parseInt(request.getParameter("role"));
		CompanyMemberDTO cmemberVO = new CompanyMemberDTO(company_name, company_pwd, company_tel, company_email, role_id);
		companyMemberDAO.addCompanyMember(cmemberVO);
		System.out.println("업체회원이 추가 되었습니다.");
		
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}
	

}
