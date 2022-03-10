<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>마이페이지 | HeendyFoody </title>
<link href="${contextPath}/static/css/common.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/static/css/css-library.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/static/css/member.min.css" rel="stylesheet" type="text/css">
<link href="${contextPath}/static/css/mypage.min.css" rel="stylesheet" type="text/css">
</head>

<body>
  <div id="wrap" class="mypage mypage main">
    <div id="contents">
	  <div class="innercon">
	    <section class="lnbarea">
		  <h2>마이페이지</h2>
		  <ul>
		    <li class="lnb-depth1">
			  <a href="#">활동 관리</a>
			  <ul class="lnb-depth2">
			    <li><a href="${contextPath}/mypage/order_list">주문 내역</a></li>
			    <li><a href="${contextPath}/mypage/wish">좋아요</a></li>
			    <li><a href="${contextPath}/mypage/recent_view">최근 본 상품</a></li>
			    <li><a href="${contextPath}/mypage/personal_info">개인정보 이용현황</a></li>
			  </ul>
		    </li>
		  </ul>
		</section> 
		
		<section class="conarea">
		  <section class="mystate-section">
		    <div class="myinfo">
		      <div class="memclass normal"> 
		        <div class="cont">
			     <strong class="name">이지민님</strong>                   
		         <ul>
		           <li><a href="${contextPath}/mypage/personal_info" class="btn-line">회원정보</a></li>
		         </ul>
		         </div>
		       </div>
		     </div>
		     <div class="point">
		       <ul>          
		         <li>
		           <a href="#">
		             <div class="inner">
		               <strong><span id="upointCurAmt">0</span>P</span></strong> H.Point
		             </div>
		           </a>
		         </li>                                                                                             
		       </ul>
		     </div>
		   </section>
		  
		   <section class="like-section">
		     <header class="header">
		       <h4><span>좋아요 </span><strong>${totalCount}</strong></h4>
		       <a href="${contextPath}/mypage/wish?pno=1" class="btn-line">더보기</a>
		     </header>
		  
		     <ul class="product-list small">
		       <c:forEach items="${wishList}" var="wishDTO">
		         <li> <!-- 정상 -->
			       <a href="${contextPath}/product/detail?pid=${wishDTO.productId}">
			         <span class="thumb">
			           <img src="${wishDTO.imageUrl}" alt="${wishDTO.productName}">
			         </span>
	                 <strong class="txt-ti ellipsis">${wishDTO.productName}</strong>
	               </a>
		           <span class="info">
		             <span class="txt-price">
			           <strong><em>${wishDTO.discountPrice}</em>원</strong>
			           <del>${wishDTO.productPrice}</del>       
		             </span>
		           </span>
		         </li>    
		       </c:forEach>  
		     </ul>
		   </section>
		   
		   <section class="view-section">
		     <header class="header">
		       <h4><span>최근 본 상품 </span><strong>18</strong></h4>
		       <a href="${contextPath}/mypage/recent_view" class="btn-line">더보기</a>
		     </header>
		     
		     <ul class="product-list small">
		       <li> <!-- 정상 -->
		         <a href="javascript:fnProductDetailMove('S02107069950', '', '100411', '01');">
		           <span class="thumb">
		             <img src="https://tohomeimage.thehyundai.com/PD/PDImages/S/9/2/2/2801001001229_00.jpg?RS=232x278" alt="[리치몬드 과자점] 까만식빵" onerror="this.src='/UIUX/w/pjtCom/images/common/noimage_232x278.jpg'">
		           </span>
		           <strong class="txt-ti ellipsis">[리치몬드 과자점] 까만식빵</strong>
		         </a>
		         <span class="info">
		           <span class="txt-price">
		             <strong><em>6,120</em>원</strong>
		             <!-- 정상가가 혜택가보다 큰 경우 -->
		             <del>7,200</del>        
			       </span>
		         </span>
		       </li>
		                           
		       <li> <!-- 정상 -->
		         <a href="javascript:fnProductDetailMove('S02107069951', '', '100411', '01');">
		           <span class="thumb">
		             <img src="https://tohomeimage.thehyundai.com/PD/PDImages/S/6/3/2/2801001001236_00.jpg?RS=232x278" alt="[리치몬드 과자점] 쿠인아망" onerror="this.src='/UIUX/w/pjtCom/images/common/noimage_232x278.jpg'">
		           </span>
		           <strong class="txt-ti ellipsis">[리치몬드 과자점] 쿠인아망</strong>
		         </a>
		         <span class="info">
		           <span class="txt-price">
		             <strong><em>3,310</em>원</strong>
		             <!-- 정상가가 혜택가보다 큰 경우 -->   
		             <del>3,900</del>   
		           </span>
		         </span>
		       </li>
		     </ul>
		   </section>
		 </section>
	   </div>
    </div>
  </div>
</body>
</html>