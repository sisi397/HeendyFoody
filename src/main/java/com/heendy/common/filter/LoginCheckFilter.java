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
 * @author 이지민, 이승준
 * 로그인 검증 필터: 인증되지 않은 접근을 막는다
 * 마이페이지, 업체, 장바구니는 로그인을 한 유저만 접근 가능
 */
@WebFilter(filterName="loginCheckFilter")
public class LoginCheckFilter implements Filter {
	
	// MeberDTO 타입의 객체를 불러오기 위한 인스턴스 변수
	private UserService<MemberDTO, HttpSession> userService = SessionUserService.getInstance();

    public LoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//request와 세션 가져와 세팅
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		
		try {
			/*UserService를 이용해 세션에서 유저 객체 가져오기
			 있다면 정상적으로 하던 작업 진행*/
			MemberDTO member = userService.loadUser(session).orElseThrow(MemberNotExistSession::new);
			System.out.println(member.getMemberName() + "$$$$$$$$$$$logincheckfilter$$$$$");
			req.setAttribute("loginUser", member);
			chain.doFilter(request, response);
			
			//없다면 로그인 페이지로 돌린다
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
