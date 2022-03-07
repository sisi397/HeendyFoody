<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>

<%
request.setCharacterEncoding("UTF-8");
%>    

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>흰디푸디 투홈</title>
</head>

<body>

	<div id="contents">
			<h2>로그인</h2>
			<p class="txt">현대식품관 전용회원은 아이디를 이메일로 입력해 주세요.</p>

			<form name="memberLoginForm" action="" method="post">
				<input type="hidden" name="redirectUrl"
					value="https://tohome.thehyundai.com/front/dp/dpf/customerCenterMain.do?UseCache=N" />
				<input type="hidden" name="popupYn" value="N" /> <input
					type="hidden" name="token" /> <input type="hidden" name="snsType" />
				<input type="hidden" name="hpointTabYn" value="N" /> <input
					type="hidden" name="autoCheck" value="7412AC0749CDC0284DFFC39F9054F27D" /> <input
					type="hidden" name="august" value="3110B095B3DBCD1D232FFFD7EC87000C" /> <input
					type='hidden' name='autoCheck'
					value=''>
				<fieldset class="form-field">
					<legend class="hide">로그인</legend>

					<ul>
						<li><label class="form-entry"> <input type="text"
								id="id" name="id" class="big" title="아이디 입력" placeholder="아이디"
								value=''
								onkeydown="javascript:fn.inputMsgClear('#id');">
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
						id="idSaveYn" name="idSaveYn" class="big" checked><span>아이디저장</span></label>
					<!-- <label class="form-save" id='autoLoginLabel' style='display: none'><input type="checkbox" id='autoLoginYn'  class="big"><span>자동로그인</span></label> -->

					<ul class="btn-group login-surport">
						<li><a
							href="/front/cua/front/joinStep1.do?publicYn=Y">회원가입</a></li>
						<li><a href="/front/cua/front/findIdPwd.do">아이디/비밀번호
								찾기</a></li>
					</ul>

					<button type="button" class="btn fill big black"
						onclick="javascript:goLogin();">로그인</button>
				</fieldset>
			</form>
	</div>

</body>
</html>