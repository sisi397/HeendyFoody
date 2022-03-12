/**
* --------------------------------
* MEMBER JS
* --------------------------------
*/

//회원가입시 제약조건 설정
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

function id_check(){
  if (document.joinMemberForm.name.value == "") {
    alert('사용자 아이디(이름)를 입력하여 주십시오.');
    document.joinMemberForm.name.focus();
    return;
  }
  var url = "idCheck.do?name=" + document.joinMemberForm.name.value;		//입력한 아이디(이름) 받아서 액션에 전달
  window.open( url, "_blank", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=300, height=50");
}