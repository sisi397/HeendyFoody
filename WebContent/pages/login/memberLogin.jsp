<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>흰디푸디 투홈</title>
<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css"
	href="../../static/css/common/common.min.css">

<!-- 멤버 Login CSS -->
<link rel="stylesheet" type="text/css"
	href="../../static/css/common/member.min.css">

</head>
<jsp:include page="../../header.jsp" flush="false" />
<!-- 서버에서 저장한 attribute를 가져온다. -->
<%
String msg = (String) request.getAttribute("msg");
Cookie[] c = request.getCookies();
String cookieVal = "";
if (c != null) {
	for (Cookie i : c) {
		if (i.getName().equals("saveId")) {
	cookieVal = i.getValue(); // 쿠키에 들어있는 값 불러오기
		}
	}
}
%>
<script type="text/javascript">
	function check_login_input() {
		if (document.memberLoginForm.id.value == "") {
			alert("아이디를 입력하세요.");
			document.memberLoginForm.id.focus();
		} else if (document.memberLoginForm.pwd.value == "") {
			alert("비밀번호를 입력하세요.");
		} else {
			document.memberLoginForm.submit();
		}
	}
</script>
<div id="wrap" class="member login">
	<div id="contents">
		<div class="innercon">
			<h2>로그인</h2>
			<p class="txt">흰디푸드 아이디를 입력해 주세요.</p>

			<form name="memberLoginForm"
				action="${contextPath}/member/loginMember.do" method="post">
				<fieldset class="form-field">
					<legend class="hide">로그인</legend>

					<ul>
						<li><label class="form-entry"> <input type="text"
								id="id" name="id" class="big" title="아이디 입력" placeholder="아이디"
								value="<%=cookieVal != "" ? cookieVal : ""%>"
								onkeydown="javascript:fn.inputMsgClear('#id');"> <!-- cookieVal이 ""이 아니라면 cookieVal값을 넣고 아니라면 "" -->
								<button type="button" class="btn-del">삭제</button>
						</label></li>
						<li><label class="form-entry"> <input type="password"
								id="pwd" name="pwd" class="big" title="비밀번호 입력"
								placeholder="비밀번호" value=''
								onkeydown="javascript:fn.inputMsgClear('#pwd');">
								<button type="button" class="btn-del">삭제</button>
						</label></li>
					</ul>

					<label class="form-save"><input type="checkbox"
						id="idSaveYn" name="idSaveYn" class="big"
						<%=cookieVal != "" ? "checked" : ""%>><span>아이디저장</span></label>
					<!-- 쿠키에 저장된 값이 있다면 체크 상태로 둔다. 아니라면 "" -->

					<ul class="btn-group login-surport">
						<li><a href="${contextPath}/pages/login/memberJoin.jsp">회원가입</a></li>
						<li><a href="${contextPath}/pages/login/findId.jsp"
							onclick="window.open(this.href, '_blank', 'width=450, height=150'); return false;">아이디
								찾기</a></li>
						<li><a href="${contextPath}/pages/login/findPw.jsp"
							onclick="window.open(this.href, '_blank', 'width=450, height=200'); return false;">비밀번호
								찾기</a></li>
					</ul>
					<button type="button" id="btnLogin" class="btn fill big black" onclick="check_login_input()">로그인</button>
				</fieldset>
			</form>
		</div>
	</div>
</div>

<jsp:include page="../../footer.jsp" flush="false" />
</html>