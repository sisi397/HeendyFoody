package com.heendy.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

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

import com.heendy.common.exception.MemberNotExistSession;
import com.heendy.dto.MemberDTO;
import com.heendy.utils.SessionUserService;
import com.heendy.utils.UserService;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter(filterName="loginCheckFilter")
public class LoginCheckFilter implements Filter {

	private UserService<MemberDTO, HttpSession> userService = SessionUserService.getInstance();
    /**
     * Default constructor. 
     */
    public LoginCheckFilter() {
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
		
		HttpServletRequest req = (HttpServletRequest)request;
		
		HttpSession session = req.getSession();
		
		
		try {
			
			MemberDTO member = userService.loadUser(session).orElseThrow(MemberNotExistSession::new);
			System.out.println(member.getMemberName() + "$$$$$$$$$$$logincheckfilter$$$$$");
			req.setAttribute("loginUser", member);
			chain.doFilter(request, response);
			
		} catch(MemberNotExistSession e) {
			HttpServletResponse res = (HttpServletResponse)response;
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ req.getContextPath();
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스입니다');location.href='"+url+"/member/memberLogin.do'</script>");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
