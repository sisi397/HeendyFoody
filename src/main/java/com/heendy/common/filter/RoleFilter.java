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
 * Servlet Filter implementation class RoleFilter
 */
@WebFilter(filterName="roleFilter", urlPatterns = {
		"/mypage/*",
		"/cart/shoppingCartList.do",
		"/company/*",
        })
public class RoleFilter implements Filter {
	
	
	private UserService<MemberDTO, HttpSession> userService = SessionUserService.getInstance();
    /**
     * Default constructor. 
     */
    public RoleFilter() {
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
		
		MemberDTO member = (MemberDTO)req.getAttribute("loginUser");

		String toPath = req.getServletPath();
		
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ req.getContextPath();
		
		if (member.getRoleId() == 2 && toPath.equals("/company")) {
			HttpServletResponse res = (HttpServletResponse)response;
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>alert('접근할 수 없는 페이지입니다');location.href='"+url+"'</script>");
		} else if (member.getRoleId() == 1 && !toPath.equals("/company")) {
			HttpServletResponse res = (HttpServletResponse)response;
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>alert('접근할 수 없는 페이지입니다');location.href='"+url+"/company/company.do'</script>");
						
		} else {
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
