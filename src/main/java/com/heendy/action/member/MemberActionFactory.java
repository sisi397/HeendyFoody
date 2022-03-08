package com.heendy.action.member;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;

public class MemberActionFactory implements ActionFactory{
	
	private final static ActionFactory factory = new MemberActionFactory();
	private MemberActionFactory() {
		
	} 
	
	public static ActionFactory getInstance() {
		return factory;
	}
	
	@Override
	public Action getAction(String command) {
		Action action = null;
		if (command == "/addMember.do") {
			action =  new AddMemberAction();
		}else if(command == "/loginMember.do") {
			action = new LoginMemberAction();
		}
		return action;
	}

}
