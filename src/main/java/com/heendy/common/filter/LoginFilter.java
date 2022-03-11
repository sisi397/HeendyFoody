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
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = {"/member/memberJoin.do", "/member/memberLogin.do"})
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//로그인된 세션(클라이언트)인지 확인(HttpSession객체를 얻으려면 HttpServletRequest가 필요.)
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		MemberDTO loginUser= (MemberDTO)session.getAttribute("loginUser");	//loginUser DTO객체 받아오기
		
		//로그인 안 된 상태라면 흐름 이어가기
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
