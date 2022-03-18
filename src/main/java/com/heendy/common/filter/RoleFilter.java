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

import com.google.gson.Gson;
import com.heendy.common.ErrorCode;
import com.heendy.common.ErrorResponse;
import com.heendy.common.exception.MemberNotExistSession;
import com.heendy.dto.MemberDTO;



/**
 * @author 이지민, 이승준
 * 권한 검증 필터: 권한에 따라 다른 페이지 접근 여부를 확인한다
 */
@WebFilter(filterName="roleFilter")
public class RoleFilter implements Filter {
	
    public RoleFilter() {
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

			
		//로그인된 세션(클라이언트)인지 확인(HttpSession객체를 얻으려면 HttpServletRequest가 필요.)
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		
		//롤 필터에서 넘겨준 loginUser 객체를 가져와 member 변수에 저장
		MemberDTO member = (MemberDTO)req.getAttribute("loginUser");
		System.out.println(member.getMemberName() +"^^^^^^^^^^^rolefilter^^^^^^^^^^^^^^");

		//확인할 경로 변수 설정
		String toPath = req.getServletPath(); //서블릿 경로
		String requestPath = req.getRequestURI().substring(req.getContextPath().length()); //ContextPath를 제외한 경로
		System.out.println("requestPath: " + requestPath);

		

		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ req.getContextPath();

		//유저가 업체 + 고객 관련 api 경로들에 접근한 경우엔
		if (member.getRoleId() == 1 && (
				requestPath.equals("/cart/create.do") || 
				requestPath.equals("/cart/addCount.do") ||
				requestPath.equals("/cart/minusCount.do") ||
				requestPath.equals("/cart/delete.do") ||
				toPath.equals("/wish") ||
				toPath.equals("/order") ||
				requestPath.equals("/product/select.do") 
				)) { 
			
			//권한이 없다는 에러 코드를 res에 담아 보낸다
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.FORBIDDEN_USER);
			String json = new Gson().toJson(errorResponse);
			res.setStatus(errorResponse.getStatus());
			res.getWriter().write(json);
			
		//유저가 고객 + 업체 관련 api 경로들에 접근한 경우에도
		} else if (member.getRoleId() == 2 && (
				requestPath.equals("/company/createProduct.do") ||
				requestPath.equals("/company/orderinfoChart.do") || 
				requestPath.equals("/company/productList.do") )) {
			
			//마찬가지로 권한이 없다는 에러 코드를 res에 담아 보낸다
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.FORBIDDEN_USER);
			String json = new Gson().toJson(errorResponse);
			res.setStatus(errorResponse.getStatus());
			res.getWriter().write(json);
		
		//유저가 고객 + 업체 관련 경로들에 접근한 경우에 메인페이지로 redirect
		} else if (member.getRoleId() == 2 && toPath.equals("/company")) {


			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();

			out.println("<script>alert('접근할 수 없는 페이지입니다');location.href='"+url+"'</script>");

		//유저가 업체 + 업체와 관련 없는 경로들에 접근한 경우에 업체 메인페이지로 redirect
		} else if (member.getRoleId() == 1 && !toPath.equals("/company")) {

			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>alert('접근할 수 없는 페이지입니다');location.href='"+url+"/company/company.do'</script>");
			
		//이외의 경우라면 정상적으로 하던 작업 이어 진행
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
