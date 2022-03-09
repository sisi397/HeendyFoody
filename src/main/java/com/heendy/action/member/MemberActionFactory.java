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
		System.out.println("getAction의 command : " + command);
		if (command.equals("/index.do")) {
			action = new IndexAction();
		}
		
		if (command.equals("/addMember.do")) {
			action =  new AddMemberAction();
		}else if(command.equals("/loginMember.do")) {
			action = new LoginMemberAction();
		}else if(command.equals("/idCheck.do")) {
			action = new IdCheckAction();
		}else if(command.equals("/logout.do")) {
			action = new LogoutAction();
		}
		return action;
	}

}
