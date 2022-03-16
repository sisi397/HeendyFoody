package com.heendy.action.main;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;
import com.heendy.action.member.IndexAction;


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
