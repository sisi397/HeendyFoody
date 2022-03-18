<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

    
    
<section class="lnbarea">
  <h2>마이페이지</h2>
  <ul>
	<li class="lnb-depth1">
	  <a href="${contextPath}/mypage/info.do">활동 관리</a>
	  <ul class="lnb-depth2">
	    <li><a href="${contextPath}/mypage/order_list.do">주문 내역</a></li>
		<li><a href="${contextPath}/mypage/wish.do">좋아요</a></li>
		<li><a href="${contextPath}/mypage/recent_view.do">최근 본 상품</a></li>
      </ul>
    </li>
  </ul>
</section> 