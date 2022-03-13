<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="http://code.jquery.com/jquery-2.2.1.min.js"></script>
</head>
<body>


	<jsp:include page="header.jsp" flush="false" />

	<jsp:include page="footer.jsp" flush="false" />
	
<a href="${contextPath }/product/list.do?menu=best">베스트</a>
<a href="${contextPath }/product/list.do?menu=sale">세일</a>
<a href="${contextPath }/product/list.do?menu=newprod">신상품</a>
<a href="${contextPath }/product/list.do?menu=category&pcate=1&cate=1">과일</a>
<a href="${contextPath }/product/list.do?menu=category&pcate=1&cate=9">계절과일</a>
<p>
<script>
</script>
</body>
</html>