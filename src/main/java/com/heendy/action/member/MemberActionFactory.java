package com.heendy.action.member;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;

public class MemberActionFactory implements ActionFactory{
	
	private final static ActionFactory factory = new MemberActionFactory();
	private MemberActionFactory() {} 
	
	public static ActionFactory getInstance() {
		return factory;
	}
	
	@Override
	public Action getAction(String command) {
		Action action = null;
		if (command.equals("/index.do")) {	//메인페이지 호출
			action = new IndexAction();
		}
		
		if (command.equals("/addMember.do")) {	// 회원 가입
			action =  new AddMemberAction();
		}else if(command.equals("/loginMember.do")) {	//로그인
			action = new LoginMemberAction();
		}else if(command.equals("/idCheck.do")) {	//아이디 중복 확인
			action = new IdCheckAction();
		}else if(command.equals("/logout.do")) {	//로그아웃
			action = new LogoutAction();
		}else if(command.equals("/findMemberId.do")) {	//아이디 찾기
			action = new FindMemberIdAction();
		}else if(command.equals("/findMemberPw.do")) {	//아이디 찾기
			action = new FindMemberPwAction();
		}
		return action;
	}

}
