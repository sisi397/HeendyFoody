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

import com.heendy.dto.MemberDTO;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = {"/member/memberJoin.do", "/member/memberLogin.do", "/mypage/*" }) //"/company/*"
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
		//CompanyMemberDTO loginCompanyUser = (CompanyMemberDTO)session.getAttribute("loginCompanyUser");
		
		String toPath = req.getServletPath();
		
		System.out.println(System.getProperty("os.name"));
	
		
		//로그인 X + 로그인 회원가입 > 흐름 이어가기
		//if((loginUser == null || loginCompanyUser == null) && toPath.equals("/member")) {
		if(loginUser == null && toPath.equals("/member")) {
			chain.doFilter(request, response);
		
		//로그인 O + 로그인 회원가입 > 홈으로 리다이렉트
		//}else if ((loginUser != null || loginCompanyUser != null)&& toPath.equals("/member")) {	
		}else if (loginUser != null && toPath.equals("/member")) {	
			HttpServletResponse res = (HttpServletResponse)response;
			String contextPath = req.getContextPath();
			res.sendRedirect(contextPath + "/index.jsp");
			
		//로그인 X + 마이페이지 > 로그인 리다이렉트
		} else if (loginUser == null && toPath.equals("/mypage")) {
			HttpServletResponse res = (HttpServletResponse)response;
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스입니다');location.href='http://localhost:8090/HeendyFoody/member/memberLogin.do'</script>");
//			String contextPath = req.getContextPath();
//			res.sendRedirect(contextPath + "/member/memberLogin.do");
			
		//로그인 O + 마이페이지 > 흐름 이어가기
		} else if (loginUser != null && toPath.equals("/mypage")) {
			chain.doFilter(request, response);
		} //<--- 밑 주석 해제하면 삭제할 }
		
		//업체 로그인 O + 업체 페이지 > 흐름 이어가기
//		} else if (loginCompanyUser != null && toPath.equals("/company")) {
//			chain.doFilter(request, response);
		
//		} else if (loginCompanyUser != null && !toPath.equals("/company")) {
//			HttpServletResponse res = (HttpServletResponse)response;
//			res.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = res.getWriter();
//			out.println("<script>alert('접근할 수 없는 페이지입니다');location.href='http://localhost:8090/HeendyFoody/company/'</script>");
//		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
