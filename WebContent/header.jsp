<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

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
<title>Insert title here</title>

<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/static/css/common/common.min.css">
</head>
<body>

	<!-- header// -->
	<header id="header" class="short">

		<div class="inner">
			<!-- toparea// -->
			<div class="toparea">
				<h1>
					<a href="${contextPath}/index.jsp"><img
						src="${contextPath}/static/images/common/header_logo.png"
						alt="현대식품관"></a>
				</h1>

				<div class="util">
					<c:choose>
						<c:when test="${empty sessionScope.loginUser}">
							<!-- 로그인 전 -->
							<a href="${contextPath}/pages/login/memberLogin.jsp">로그인</a>
							<a href="${contextPath}/pages/login/memberJoin.jsp">회원가입</a>
						</c:when>
						<c:otherwise>
							<!-- 로그인 후 -->
							<li style="color: orange">
							로그인 여부 : ${sessionScope.isLogin}
								${sessionScope.loginUser.getMemberName()}(${sessionScope.loginUser.getMemberId()})</li>
							<a href="${contextPath}/member/logout.do">로그아웃</a>
						</c:otherwise>
					</c:choose>
					<a href="#">마이페이지</a>
				</div>

			</div>
			<!-- //toparea -->
		</div>
	</header>

</body>
</html>