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
<title>Insert title here</title>
</head>
<body>

<!-- header// -->
<header id="header" class="short">
    <div class="skip">
        <a href="#contents">본문으로 건너뛰기</a>
    </div>
    
    <div class="inner">
        <!-- toparea// -->
        <div class="toparea">
            <h1><a href="${contextPath}"><img src="../../static/images/common/header_logo.png" alt="현대식품관"></a></h1>
            
            <div class="util">
            
                        <!-- util : 로그인 전// -->
                        <a href="${contextPath}/pages/login/memberLogin.jsp">로그인</a>
                        <a href="${contextPath}/pages/login/memberJoin.jsp">회원가입</a>
                        <!-- //util : 로그인 전 -->
                    
                
                <a href="#">마이페이지</a>
            </div>
            
        </div>
        <!-- //toparea -->
    </div>
</header>

</body>
</html>