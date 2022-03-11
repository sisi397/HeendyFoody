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
<title>흰디푸디 아이디 찾기</title>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/static/css/common/common.min.css">
<link rel="stylesheet" type="text/css"
	href="${contextPath}/static/css/common/css-library.min.css">
<script>
function check_input(){
	if(document.findIdForm.email.value == ""){
		alert("이메일을 입력하지 않았습니다.");
		document.findIdForm.email.focus();
	}else{
		document.findIdForm.submit();
	}
}
</script>
</head>
<body>
<div id="popup">
  <h2>아이디 찾기</h2>
  <form method=post name=findIdForm action="${contextPath}/member/findMemberId.do" >
    이메일 주소 : 
  <input name="email" type="text" style="width: 300px">
  <button type="button" class="btn fill black" onclick="check_input()">찾기</button>
  </form>
  <br>
  
<!--   찾은 경우 화면에 표시 -->
  <c:if test="${userName != null}">
    <div id="result">사용자 아이디: ${userName}</div>
  </c:if>
</div>
</body>
</html>