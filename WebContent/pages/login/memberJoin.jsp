<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- 현재 날짜 -->
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="date">
	<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />
</c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>흰디푸디 투홈</title>

</head>

<jsp:include page="../../header.jsp" flush="false" />
<div id="contents" style="width: 300px; margin: 0 auto;">
	<h2>흰디푸디 회원가입</h2>

	<div class="container">
		<p class="txt" style="margin: 10px 0px 20px;">가입정보를 입력해 주세요. (* 필수
			입력사항)</p>
			
		<div style="margin-bottom: 30px;">
			<input type="radio" name="checkRadio" value="normal"
				checked="checked" style="position: inherit;">일반회원 <input
				type="radio" name="checkRadio" value="company"
				style="position: inherit; margin-left: 100px">업체 회원
		</div>
		
		<!-- 선택한 라디오 박스에 따라 폼 변경 -->
		<form name="joinMemberForm" id="normalForm"
			action="${contextPath}/member/addMember.do" method="post">
			<input type="hidden" name="role" id="company_role" value="2">
			<table>

				<tr>
					<td width="400"><p>사용자 아이디 *</td>
				</tr>
				<tr>
					<td width="400"><input type="text" name="name"
						style="width: 310px"> <input type="hidden"
						name="hidden_name">
						<button type="button" class="btn fill black" onclick="id_check()">중복확인</button>
					</td>
				</tr>
				<tr>
					<td width="200"><p>비밀번호 *</td>
				</tr>
				<tr>
					<td width="400"><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td width="200"><p>비밀번호 확인 *</td>
				</tr>
				<tr>
					<td width="400"><input type="password" name="chkpwd"></td>
				</tr>
				<tr>
					<td width="200"><p>이메일 *</td>
				</tr>
				<tr>
					<td width="400"><input type="text" name="email"></td>
				</tr>
				<tr>
					<td width="200"><p>생년월일 *</td>
				</tr>
				<tr>
					<td width="400">
						<!--  생년월일 입력 오늘 이후로는 기입할 수 없음 --> <input type="date" id="bd"
						max="${date}" name="birthdate">
					</td>
				</tr>
				<tr>
					<td width="200"><p>주소</td>
				</tr>
				<tr>
					<td width="400"><input type="text" name="address"></td>
				</tr>
				<tr>
				</tr>

				<tr>
					<td width="400">
						<div style="margin: 0 auto; padding-top: 30px;">
							<button type="button" class="btn fill big black"
								style="margin-right: 200px" onclick="join_save()">가입하기</button>
							<button type="reset" class="btn fill big black">다시입력</button>
						</div>
					<td>
				</tr>
			</table>
		</form>
		<!-- 업체 로그인 폼 -->
		<form name="joinCompanyForm" id="companyForm" style="display: none"
			action="${contextPath}/member/addCompanyMember.do" method="post">
			<input type="hidden" name="role" id="nomal_role" value="2">
			<table>
				<tr>
					<td width="400"><p>업체 아이디(이름) *</td>
				</tr>
				<tr>
					<td width="400"><input type="text" name="company_name"
						style="width: 310px"> <input type="hidden"
						name="hidden_company_name">
						<button type="button" class="btn fill black"
							onclick="company_id_check()">중복확인</button></td>
				</tr>
				<tr>
					<td width="200"><p>업체 비밀번호 *</td>
				</tr>
				<tr>
					<td width="400"><input type="password" name="company_pwd"></td>
				</tr>
				<tr>
					<td width="200"><p>비밀번호 확인 *</td>
				</tr>
				<tr>
					<td width="400"><input type="password" name="company_chkpwd"></td>
				</tr>
				<tr>
					<td width="200"><p>업체 전화번호 *</td>
				</tr>
				<tr>
					<td width="400"><input type="text" name="company_tel"></td>
				</tr>
				<tr>
					<td width="200"><p>업체 이메일*</td>
				</tr>
				<tr>
					<td width="400"><input type="text" name="company_email"></td>
				</tr>
				<tr>
				</tr>

				<tr>
					<td width="400">
						<div style="margin: 0 auto; padding-top: 30px;">
							<button type="button" class="btn fill big black"
								style="margin-right: 200px" onclick="company_join_save()">가입하기</button>
							<button type="reset" class="btn fill big black">다시입력</button>
						</div>
					<td>
				</tr>
			</table>
		</form>
	</div>
</div>

<jsp:include page="../../footer.jsp" flush="false" />

</html>