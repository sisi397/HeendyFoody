package com.heendy.utils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 이지민
 * 최근 본 상품을 쿠키에 저장 및 확인하는 클래스
 * */
public class CookieUtils {
	
	//인코딩 값과 특정 url에 대해서만 쿠키 전송 가능한 path 설정
	 private static final String encoding = "UTF-8";
	 private static final String path = "/";
	 
	 
	 //저장된 쿠키를 가져와 리스트로 반환하는 메서드 
	 //key에는 찾고자 하는 쿠키 이름이 들어온다
	 public List<String> getValueList(String key, HttpServletRequest request) throws UnsupportedEncodingException{
		 
		 Cookie[] cookies = request.getCookies();
		 
		 //가져온 쿠키를 담을 변수 초기화
		 String[] cookieValues = null;
		 String value = "";
		 List<String> list = null;
		 
		 //가져온 쿠키들에 값이 있으면 
		 if (cookies != null) {
			 for (int i = 0; i < cookies.length; i++) {
				 //key값과 동일한 쿠키를 찾아 쿠키 값을 ,로 split해 담는다
				 if (cookies[i].getName().equals(key)) {
					 value = cookies[i].getValue();
					 cookieValues = (URLDecoder.decode(value, encoding)).split(",");
					 }
				 }
			 }
	  
		  //String[] --> ArrayList 변환
		  if (cookieValues != null) {
			  list = new ArrayList<String>(Arrays.asList(cookieValues));
		  }
		  return list;
	}
	 
	 
	 //값을 쿠키에 저장하는 메서드
	 //key에는 저장하고자 하는 쿠키 이름 value에는 저장할 값이 들어온다
	 public void setCookie(String key, String value, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		 
		 //쿠키에 담긴 값들을 리스트로 가져온다
		 List<String> list = getValueList(key, request);
		 
		 //쿠키 값을 담을 변수 초기화
		 String sumValue = "";
	  
		 
		 //리스트에 쿠키 값이 있다면
		 if (list != null) {
			 //value와 같은 값이 있다면 해당 elem 삭제
			 list.removeIf(elem -> elem.equals(value));
			 //리스트를 돌면서 값들을 다시 하나의 스트링으로 만드는 작업
			 for (String v: list) {
				 sumValue += v + ",";
			 }
			 sumValue += value;
			 
		 //리스트에 아무 쿠키 값도 없다면
		 } else {
			 sumValue = value;
		 }
      

		 //sumValue 스트링을 쿠키에 저장
		 if (!sumValue.equals("")) {
			 Cookie cookie = new Cookie(key, URLEncoder.encode(sumValue, encoding));
			 cookie.setMaxAge(-1); //브라우저 종료 시 해당 쿠키는 삭제된다
			 cookie.setPath(path);
			 response.addCookie(cookie);
		 }
	 }
}
