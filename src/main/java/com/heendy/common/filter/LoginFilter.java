package com.heendy.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.heendy.dto.MemberDTO;

/**
 * @author 문석호
 * 로그인 검증 필터 세션이 로그인 한 기록을 가지고 있다면 
 * 접근을 막아야할 action을 담는다.
 * 막은 접근은 메인페이지로 redirect 한다.
 *
 */
@WebFilter(urlPatterns = {
		"/member/memberJoin.do", 	//로그인 페이지로 이동 
		"/member/memberLogin.do",	//회원가입 페이지로 이동
		"/member/addMember.do",		//회원가입 기능
		"/member/loginMember.do", 	//로그인 기능
		"/member/idCheck.do",		//아이디 중복 확인 기능
		"/member/findMemberId.do",	//아이디 찾기 기능
		"/member/findMemberPw.do",	//비밀번호 찾기 기능
		"/member/addCompanyMember.do",	//업체 회원가입 기능
		"/member/loginCompanyMember.do"	//업체 로그인 기능
		})
public class LoginFilter implements Filter {

    public LoginFilter() {
    }


	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//로그인된 세션(클라이언트)인지 확인(HttpSession객체를 얻으려면 HttpServletRequest가 필요.)
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		MemberDTO loginUser= (MemberDTO)session.getAttribute("loginUser");	//loginUser DTO객체 받아오기
		
		//로그인 안 된 상태라면 흐름 이어가기 (로그인 안 한 상태에서 멤버/업체 회원가입, 멤버/업체 로그인 액션은 수행 필요)
		if(loginUser == null) {
			chain.doFilter(request, response);
		}else {
			//로그인상태라면 홈으로 리다이렉트
			HttpServletResponse res = (HttpServletResponse)response;
			String contextPath = req.getContextPath();
			res.sendRedirect(contextPath + "/index.jsp");
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
