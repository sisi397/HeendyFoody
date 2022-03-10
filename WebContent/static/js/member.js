/**
* --------------------------------
* MEMBER JS
* --------------------------------
*/

//회원가입시 제약조건 설정
function login_save() {
  if (document.joinMemberForm.name.value == "") {
    alert("아이디를 입력하지 않았습니다.");
    document.joinMemberForm.name.focus();
	}
	else if (document.joinMemberForm.pwd.value == "") {
    alert("비밀번호를 입력하지 않았습니다.");
    document.joinMemberForm.pwd.focus();
  } else if ((document.joinMemberForm.pwd.value != document.joinMemberForm.chkpwd.value)) {
    alert("비밀번호가 일치하지 않습니다.");
    document.joinMemberForm.pwd.focus();
  } else if (document.joinMemberForm.email.value == "") {
    alert("이메일을 입력하지 않았습니다.");
    document.joinMemberForm.email.focus();
  } else {
    document.joinMemberForm.submit();
  }
}