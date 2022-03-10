<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 검사</title>
<script type="text/javascript">
	function is_ok() {
		opener.joinMemberForm.name.value = "${id}"; //opener는 부모창의 객체를 참조한다. name과 hidden_id에 이름 설정
		opener.joinMemberForm.hidden_name.value = "${id}";
		self.close(); //창 닫기
	}
</script>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/static/css/common/common.min.css">
</head>
<body>
	<div>
		<!-- 사용 가능한 아이디 -->
		<c:if test="${result == 1}">	
			<h3>${id}는 사용 가능한 아이디 입니다.</h3>
			<input type="button" class="btn fill black" value="사용하기" onclick="is_ok()">
		</c:if>
		<c:if test="${result == 0}">	
			<h2>${id}는 이미 사용중인 아이디입니다.</h2>
			<script type="text/javascript">
				opener.document.joinMemberForm.name.value = "";
			</script>
		</c:if>
	</div>
</body>
</html>