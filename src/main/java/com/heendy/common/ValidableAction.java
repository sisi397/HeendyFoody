package com.heendy.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 * @author 이승준
 * @deprecated ValidRequireAction class로 request 파라미터를 검증합니다.
 * 
 * request 의 파라미터를 검증 기능을 하는 인터페이스	
 * */
public interface ValidableAction {
	
	/**
	 * @author 이승준
	 * 
	 * @param reqeust 
	 * @return List<ErrorResponse.ErrorField>
	 * 
	 * request의 파라미터 검증 작업을 하는 메소드
	 * */
	List<ErrorResponse.ErrorField> valid(HttpServletRequest request);
}
