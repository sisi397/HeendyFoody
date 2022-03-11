package com.heendy.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/HeendyFoody/*")
public class EncodingFilter implements Filter {
	private String encoding = "utf-8";	//필드에 encoding변수 저장
    /**
     * Default constructor. 
     */
    public EncodingFilter() {
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
		System.out.println("인코딩 필터 동작중...");
		//요청 인코딩이 설정되지 않은 경우
		if(request.getCharacterEncoding() == null) {
			//인코딩 설정하기
			request.setCharacterEncoding(encoding);
		}
		//요청 인코딩이 설정됐다면 흐름 이어가기
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// 필터 최초 실행 시 encoding을 utf-8로 설정
		encoding = fConfig.getInitParameter("utf-8");
	}

}
