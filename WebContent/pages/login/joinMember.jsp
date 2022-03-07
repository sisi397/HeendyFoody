<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"  />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>흰디푸디 투홈</title>
</head>

<body>

	<div id="contents">
		<h2>회원가입</h2>
		<p class="txt">현대식품관 전용회원은 아이디를 이메일로 입력해 주세요.</p>

		<form name="joinMemberForm" action="${contextPath}/member/addMember.do" method="post">
			<table align="center">
				<tr>
					<td width="200"><p align="right">사용자 이름</td>
					<td width="400"><input type="text" name="name"></td>
				</tr>
				<tr>
					<td width="200"><p align="right">비밀번호</td>
					<td width="400"><input type="password" name="pwd"></td>
				</tr>

				<tr>
					<td width="200"><p align="right">이메일</td>
					<td width="400"><p>
							<input type="text" name="email"></td>
				</tr>
				<tr>
					<td width="200"><p align="right">주소</td>
					<td width="400"><p>
							<input type="text" name="address"></td>
				</tr>
				<tr>
					<td width="200"><p align="right">관리자 유무</td>
					<td width="400"><p>
							<input type="text" name="role"></td>
				</tr>
				<tr>
					<td width="200"><p>&nbsp;</p></td>
					<td width="400"><input type="submit" value="가입하기"> <input
						type="reset" value="다시입력"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>