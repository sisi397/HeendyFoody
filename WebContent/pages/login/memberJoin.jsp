<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="../../static/css/common/common.min.css">  <!-- 공통 CSS -->
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script type="text/javascript" src="../../static/js/function.min.js"></script>
<title>흰디푸디 투홈</title>
</head>

<body>
	<jsp:include page="../../header.jsp" flush="false" />
	<div id="contents" style="width: 300px; margin: 0 auto;">
		<h2>현대식품관 회원가입</h2>

		<div class="container">
			<p class="txt">가입정보를 입력해 주세요.</p>
			<form name="joinMemberForm"
				action="${contextPath}/member/addMember.do" method="post">
				<table align="center">
					<tr>
						<td width="200"><p>사용자 아이디 *</td>
					</tr>
					<tr>
						<td width="400"><input type="text" name="name"></td>
					</tr>
					<tr>
						<td width="200"><p>비밀번호 *</td>
					</tr>
					<tr>
						<td width="400"><input type="password" name="pwd"></td>
					</tr>

					<tr>
						<td width="200"><p>이메일</td>
					</tr>
					<tr>
						<td width="400"><input type="text" name="email"></td>
					</tr>
					<tr>
						<td width="200"><p>주소</td>
					</tr>
					<tr>
						<td width="400"><input type="text" name="address"></td>
					</tr>
					<tr>
						<td width="200"><p>관리자 유무 *</td>
					</tr>
					<tr>
						<td width="400"><input type="text" name="role"></td>
					</tr>
					<tr>
						<td width="400"><button type="submit" class="btn fill big black">가입하기</button>
						<button type="reset" class="btn fill big black">다시입력</button><td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<jsp:include page="../../footer.jsp" flush="false" />

</body>
</html>