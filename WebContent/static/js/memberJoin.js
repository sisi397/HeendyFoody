/**
* --------------------------------
* MEMBER JS
* --------------------------------
*/
$(document).ready(function(){
	$("input[name='checkRadio']:radio").change(function(){	//checkRadio에 변경이 발생한경우
		 var serviceType = this.value;	// 라디오버튼의 value를 가져온다.
		if(serviceType == "normal"){ // 일반회원 가입의 경우
			$('#normalForm').show();
			$('#companyForm').hide();
		}else{	//업체회원 가입의 경우
			$('#companyForm').show();
			$('#normalForm').hide();
		}
	})
})

//일반 회원가입시 제약조건 설정
function join_save() {
  if (document.joinMemberForm.name.value == "") {
    alert("아이디를 입력하지 않았습니다.");
    document.joinMemberForm.name.focus();
	}else if(document.joinMemberForm.name.value != document.joinMemberForm.hidden_name.value){
	alert("중복확인을 해주세요.");
	document.joinMemberForm.name.focus();
	}else if (document.joinMemberForm.pwd.value == "") {
    alert("비밀번호를 입력하지 않았습니다.");
    document.joinMemberForm.pwd.focus();
  } else if (document.joinMemberForm.pwd.value != document.joinMemberForm.chkpwd.value) {
    alert("비밀번호가 일치하지 않습니다.");
    document.joinMemberForm.pwd.focus();
  } else if (document.joinMemberForm.email.value == "") {
    alert("이메일을 입력하지 않았습니다.");
    document.joinMemberForm.email.focus();
  } else if (document.joinMemberForm.birthdate.value == ""){
	alert("생년월일을 입력하지 않았습니다.")
}else {
	alert("흰디푸디 회원 가입되셨습니다. 로그인 해주세요.");
    document.joinMemberForm.submit();
  }
}
//업체 회원가입시 제약조건 설정
function company_join_save() {
  if (document.joinCompanyForm.company_name.value == "") {
    alert("업체 아이디를 입력하지 않았습니다.");
    document.joinCompanyForm.company_name.focus();
	}else if(document.joinCompanyForm.company_name.value != document.joinCompanyForm.hidden_company_name.value){
	alert("중복확인을 해주세요.");
	document.joinCompanyForm.company_name.focus();
	}else if (document.joinCompanyForm.company_pwd.value == "") {
    alert("업체 비밀번호를 입력하지 않았습니다.");
    document.joinCompanyForm.company_pwd.focus();
  } else if (document.joinCompanyForm.company_pwd.value != document.joinCompanyForm.company_chkpwd.value) {
    alert(" 업체 비밀번호가 일치하지 않습니다.");
    document.joinCompanyForm.company_pwd.focus();
  } else if (document.joinCompanyForm.company_tel.value == "") {
    alert("업체 전화번호를 입력하지 않았습니다.");
    document.joinCompanyForm.company_email.focus();
  } else if (document.joinCompanyForm.company_email.value == ""){
	alert("업체 이메일을 입력하지 않았습니다.")
}else {
	alert("흰디푸디의 업체로 회원 가입되셨습니다. 로그인 해주세요.");
    document.joinCompanyForm.submit();
  }
}

function id_check(){
  if (document.joinMemberForm.name.value == "") {
    alert('사용자 아이디(이름)를 입력하여 주십시오.');
    document.joinMemberForm.name.focus();
    return;
  }
  var url = "idCheck.do?role_id=2&name=" + document.joinMemberForm.name.value;		//입력한 아이디(이름) 받아서 액션에 전달
  window.open( url, "_blank", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=300, height=50");
}

function company_id_check(){
  if (document.joinCompanyForm.company_name.value == "") {
    alert('업체 아이디(이름)를 입력하여 주십시오.');
    document.joinCompanyForm.company_name.focus();
    return;
  }
  var url = "idCheck.do?role_id=1&name=" + document.joinCompanyForm.company_name.value;		//입력한 아이디(이름) 받아서 액션에 전달
  window.open( url, "_blank", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=300, height=50");
}