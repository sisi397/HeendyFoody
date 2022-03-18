package com.heendy.action.main;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;
import com.heendy.action.member.IndexAction;

/**
 * @author 이지민, 김시은
 * main 페이지 관련 Action객체 생성을 위한 팩토리 클래스
 */
public class MainActionFactory implements ActionFactory {
	
	private final static ActionFactory factory = new MainActionFactory();
	private MainActionFactory() {} 
	
	public static ActionFactory getInstance() {
		return factory;
	}
	
	@Override
	public Action getAction(String command) {
		Action action = null;

		action = new IndexAction();

		return action;
	}
}
