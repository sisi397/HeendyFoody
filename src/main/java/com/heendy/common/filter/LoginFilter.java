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
 * @author 문석호
 * 로그인 검증 필터 세션이 로그인 한 기록을 가지고 있다면 
 * 접근을 막아야할 action을 담는다.
 * 막은 접근은 메인페이지로 redirect 한다.
 *
 */

@WebFilter(urlPatterns = {
		"/member/memberJoin.do", 	//로그인 페이지로 이동 
		"/member/addMember.do",		//회원가입 기능
		"/member/loginMember.do", 	//로그인 기능
		"/member/idCheck.do",		//아이디 중복 확인 기능
		"/member/findMemberId.do",	//아이디 찾기 기능
		"/member/findMemberPw.do",	//비밀번호 찾기 기능
		"/member/addCompanyMember.do",	//업체 회원가입 기능
		"/member/memberLogin.do",	//회원가입 페이지로 이동
		"/member/loginCompanyMember.do",	//업체 로그인 기능
        "/mypage/*"
        })

public class LoginFilter implements Filter {

	private UserService<MemberDTO, HttpSession> userService = SessionUserService.getInstance();
    /**
     * Default constructor. 
     */
    public LoginFilter() {
    }


	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			
			//로그인된 세션(클라이언트)인지 확인(HttpSession객체를 얻으려면 HttpServletRequest가 필요.)
			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session = req.getSession();
			MemberDTO loginUser= (MemberDTO)session.getAttribute("loginUser");	//loginUser DTO객체 받아오기
			//CompanyMemberDTO loginCompanyUser = (CompanyMemberDTO)session.getAttribute("loginCompanyUser");
			
			/*UserService를 이용해 세션에서 유저 객체 가져오기*/
			MemberDTO member = userService.loadUser(session).orElseThrow(MemberNotExistSession::new);
			
			req.setAttribute("member", member);
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
//				String contextPath = req.getContextPath();
//				res.sendRedirect(contextPath + "/member/memberLogin.do");
				
			//로그인 O + 마이페이지 > 흐름 이어가기
			} else if (loginUser != null && toPath.equals("/mypage")) {
				chain.doFilter(request, response);
			} //<--- 밑 주석 해제하면 삭제할 }
			
			//업체 로그인 O + 업체 페이지 > 흐름 이어가기
//			} else if (loginCompanyUser != null && toPath.equals("/company")) {
//				chain.doFilter(request, response);
			
//			} else if (loginCompanyUser != null && !toPath.equals("/company")) {
//				HttpServletResponse res = (HttpServletResponse)response;
//				res.setContentType("text/html; charset=UTF-8");
//				PrintWriter out = res.getWriter();
//				out.println("<script>alert('접근할 수 없는 페이지입니다');location.href='http://localhost:8090/HeendyFoody/company/'</script>");
//			}
			
		} catch(MemberNotExistSession e) {
			HttpServletResponse res = (HttpServletResponse)response;
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스입니다');location.href='http://localhost:8090/HeendyFoody/member/memberLogin.do'</script>");
		}
		

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
