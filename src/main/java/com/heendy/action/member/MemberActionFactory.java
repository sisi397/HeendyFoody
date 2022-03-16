package com.heendy.action.member;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;
import com.heendy.action.error.NotFoundViewAction;

public class MemberActionFactory implements ActionFactory {

	private final static ActionFactory factory = new MemberActionFactory();

	private MemberActionFactory() {
	}

	public static ActionFactory getInstance() {
		return factory;
	}

	@Override
	public Action getAction(String command) {
		Action action = null;
		if (command.equals("/index.do")) { // 메인 페이지 호출
			action = new IndexAction();
		} else if (command.equals("/memberLogin.do")) { // 로그인 페이지 호출
			action = new MemberLoginAction();
		} else if (command.equals("/memberJoin.do")) { // 회원가입 페이지 호출
			action = new MemberJoinAction();
		} else if (command.equals("/addMember.do")) { // 회원 가입 기능
			action = new AddMemberAction();
		} else if (command.equals("/loginMember.do")) { // 로그인 기능
			action = new LoginMemberAction();
		} else if (command.equals("/idCheck.do")) { // 아이디 중복 확인
			action = new IdCheckAction();
		} else if (command.equals("/logout.do")) { // 로그아웃
			action = new LogoutAction();
		} else if (command.equals("/findMemberId.do")) { // 아이디 찾기
			action = new FindMemberIdAction();
		} else if (command.equals("/findMemberPw.do")) { // 비밀번호 찾기
			action = new FindMemberPwAction();
		} else if (command.equals("/addCompanyMember.do")) { // 업체 회원가입
			action = new AddCompanyMemberAction();
		} else if (command.equals("/loginCompanyMember.do")) { // 업체 로그인
			action = new LoginCompanyMemberAction();
		} else {
			action = new NotFoundViewAction();
		}
		return action;
	}

}
