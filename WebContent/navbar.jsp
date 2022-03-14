<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%
request.setCharacterEncoding("UTF-8");
%>
<!-- /HeendyFoody 까지 표시 -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>흰디푸디 투홈</title>

<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/static/css/common/common.min.css">
<link rel="stylesheet" type="text/css"
	href="${contextPath}/static/css/common/css-library.min.css">
<link rel="stylesheet" type="text/css"
	href="${contextPath}/static/css/common/main.min.css">

<!-- 멤버 로그인 CSS -->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/static/css/common/member.min.css">

<!-- 공통 JS / jquery 라이브러리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/static/js/function.min.js"></script>
<script type="text/javascript" src="${contextPath}/static/js/jquery-library.min.js"></script>

<!-- main 페이지 JS -->
<script type="text/javascript" src="${contextPath}/static/js/main.min.js"></script>

<!-- 회원가입시 input 제약조건 JS -->
<script type="text/javascript" src="${contextPath}/static/js/memberJoin.js"></script>
</head>
	<!-- header// -->
	<header id="header" class="short">

		<div class="inner" style="border-bottom:1px solid #e7e7e7;">
			<!-- toparea// -->
			<div class="toparea">
				<h1 style="margin-top:0px">
					<a href="${contextPath}/member/index.do"><img
						src="${contextPath}/static/images/common/logo_header.png"
						alt="흰디푸디" class="heendy-logo-header"></a>
				</h1>

				<div class="util">
					<c:choose>
						<c:when test="${empty sessionScope.loginUser}">
							<!-- 로그인 전 보여지는 부분-->
							<a href="${contextPath}/member/memberLogin.do">로그인</a>
							<a href="${contextPath}/member/memberJoin.do">회원가입</a>
						</c:when>
						<c:otherwise>
							<!-- 로그인 후 보여지는 부분-->
							<a style="font-weight: bold">${sessionScope.loginUser.getMemberName()}님! 반갑습니다.</a>
							<a href="${contextPath}/member/logout.do">로그아웃</a>
						</c:otherwise>
					</c:choose>
					<a href="${contextPath}/mypage/info">마이페이지</a>
				</div>

			</div>
			<!-- //toparea -->
			 <!-- gnbarea// -->
       <nav class="gnbarea" style="padding:14px 0 19px;">         
           
           <!-- 팝업 : category// -->
           <div id="popCategory">
           	<button type="button" class="btn-category" style="padding-bottom:20px;">카테고리 전체보기</button>
           </div>
          	<div id ="p_popCategory" class ="popcategory">
           	<nav class="lnb-list">
           		<ul class="lnb">
           			<li class="depth1"><button type="button">과일과 채소</button>
            			<ul class="depth2">
            				<li><a>전체보기</a></li>
            			</ul>
            		</li>	
           		</ul>
          		</nav>
          	</div>
           <!-- //팝업 : category -->
           
           <!-- gnb// -->
           <ul class="gnb-list" id="homeGnbList">
           	<li><a href="${contextPath }/product/list.do?menu=best">베스트</a></li>
           	<li><a href="${contextPath }/product/list.do?menu=sale">세일</a></li>
           	<li><a href="${contextPath }/product/list.do?menu=newprod">전체상품</a></li>
           </ul>
           <!-- //gnb -->
           <button type="button" class="btn-cart" onClick="cartView();" style="top:7px;">장바구니</button>
       </nav>
		</div>
		</header>
<script>
function cartView(){
	location.href="${contextPath}/cart/shoppingCartList.do";
}
</script>