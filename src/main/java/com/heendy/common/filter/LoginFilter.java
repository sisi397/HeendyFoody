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
import java.io.PrintWriter;

import com.heendy.common.exception.MemberNotExistSession;
import com.heendy.dto.MemberDTO;
import com.heendy.utils.SessionUserService;
import com.heendy.utils.UserService;

/**
 * @author 문석호, 이지민
 * 로그인 검증 필터 세션이 로그인 한 기록을 가지고 있다면 
 * 접근을 막아야할 action을 담는다.
 * 막은 접근은 메인페이지로 redirect 한다.
 *
 */

@WebFilter(filterName="loginFilter")

public class LoginFilter implements Filter {
	// MeberDTO 타입의 객체를 불러오기 위한 인스턴스 변수
	private UserService<MemberDTO, HttpSession> userService = SessionUserService.getInstance();
    
    public LoginFilter() {
    }


	public void destroy() {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
		
			
			//로그인된 세션(클라이언트)인지 확인(HttpSession객체를 얻으려면 HttpServletRequest가 필요.)
			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session = req.getSession();

			
			/*UserService를 이용해 세션에서 유저 객체 가져오기
			 * 없다면 로그인하지 않은 것으로 간주(정상적으로 doFilter를 수행)*/
			userService.loadUser(session).orElseThrow(MemberNotExistSession::new);
			
			
			HttpServletResponse res = (HttpServletResponse)response;
			String contextPath = req.getContextPath();
			res.sendRedirect(contextPath + "/main");
				
			
		} catch(MemberNotExistSession e) {

			chain.doFilter(request, response);
		}
		

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
