package com.heendy.utils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieUtils {
	 private static final String encoding = "UTF-8";
	 private static final String path = "/";
	 
	 
	 public List<String> getValueList(String key, HttpServletRequest request) throws UnsupportedEncodingException{
	  Cookie[] cookies = request.getCookies();
	  String[] cookieValues = null;
	  String value = "";
	  List<String> list = null;
	  
	  if(cookies != null){
	   for(int i=0; i < cookies.length; i++){
	    if(cookies[i].getName().equals(key)){
	     value = cookies[i].getValue();
	     cookieValues = (URLDecoder.decode(value, encoding)).split(",");
	     break;
	    }
	   }
	  }
	  
	  // String 배열에 담겼던 값들을 List로 다시 담는다.
	  if(cookieValues != null){
	   list = new ArrayList<String>(Arrays.asList(cookieValues));
	  }
	  return list;
	 }
	 

	 public void setCookie(String key, String value, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
	  List<String> list = getValueList(key, request);
	  String sumValue = "";
	  
	  if(list != null){
	   for(int i = 0; i < list.size(); i++){
	    sumValue += list.get(i) + ",";
	   }
	   sumValue += value;
	  }
	   else{
	   sumValue = value;
	  }
	  
	  if(!sumValue.equals("")){
	   Cookie cookie = new Cookie(key, URLEncoder.encode(sumValue, encoding));
	   cookie.setMaxAge(60 * 60 * 24);
	   cookie.setPath(path);
	   response.addCookie(cookie);
	  }
	 }
}
