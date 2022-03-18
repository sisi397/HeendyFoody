<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
%>   
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- @author 문석호
 비밀번호 찾기 페이지 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>흰디푸디 비밀번호 찾기</title>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/static/css/common/common.min.css">
<link rel="stylesheet" type="text/css"
	href="${contextPath}/static/css/common/css-library.min.css">
<link rel="stylesheet" type="text/css"
href="${contextPath}/static/css/common/member.min.css">
<!-- submit 하기 전 빈 입력값 체크  -->
<script>
function check_input(){
	if(document.findPwForm.name.value == ""){
		alert("사용자 아이디를 입력하지 않았습니다.");
		document.findPwForm.name.focus();
	}else if(document.findPwForm.email.value == ""){
		alert("이메일을 입력하지 않았습니다.")
		document.findPwForm.email.focus();
	}
	else{
		document.findPwForm.submit();
	}
}
</script>
</head>
<body>
<div id="popup">
  <h2>비밀번호 찾기</h2>
  <form method=post name=findPwForm action="${contextPath}/member/findMemberPw.do" >
    사용자 아이디 : 
  <input name="name" type="text" style="width:300px"><br><br>
     이메일 주소 : 
  <input name="email" type="text" style="width:300px">

  <button type="button" class="btn fill black" onclick="check_input()" >찾기</button>
  </form>
  <br>
  
<!--   찾은 경우 화면에 표시 -->
  <c:if test="${userPw != null}">
    <div id="result">사용자 비밀번호: ${userPw}</div>
  </c:if>
</div>
</body>
</html>