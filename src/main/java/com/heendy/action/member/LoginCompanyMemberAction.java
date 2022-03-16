package com.heendy.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.heendy.action.Action;
import com.heendy.dao.CompanyMemberDAO;
import com.heendy.dao.MemberDAO;
import com.heendy.dto.CompanyMemberDTO;
import com.heendy.dto.MemberDTO;
import com.heendy.utils.SessionUserService;
import com.heendy.utils.UserService;

/**
 * @author 문석호
 * 업체 로그인 기능 구현
 */
public class LoginCompanyMemberAction implements Action {
	private final CompanyMemberDAO cmemberDAO = CompanyMemberDAO.getInstance();
	private final UserService<MemberDTO, HttpSession> userService = SessionUserService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		String company_name = request.getParameter("id");
		String company_password = request.getParameter("pwd");
		
		
		CompanyMemberDTO cmemberVO = new CompanyMemberDTO();
		cmemberVO.setCompanyName(company_name);
		cmemberVO.setCompanyPassword(company_password);
		int result = cmemberDAO.isExisted(cmemberVO);
		if(result == 1) {
			System.out.println("업체 로그인 성공");

			
			cmemberVO = cmemberDAO.getCompanyMember(company_name); //성공했으면 멤버를 조회해서 속성들을 가져온다
			HttpSession session = request.getSession();
			
			MemberDTO member = new MemberDTO();
			member.setMemberId(cmemberVO.getCompanyId());
			member.setMemberName(cmemberVO.getCompanyName());
			member.setRoleId(cmemberVO.getRole_id());
			userService.saveUser(member, session);
		
		
            
			url = request.getContextPath() + "/company/company.do";	//로그인 성공 시 이동할 페이지 지정
			response.sendRedirect(url);
			

		}else {
			System.out.println("업체 로그인 실패");
			url=request.getContextPath() + "/pages/login/loginFail.jsp";
			response.sendRedirect(url);
		}
	}

}
