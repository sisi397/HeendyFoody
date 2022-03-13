package com.heendy.action.company;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;

public class CompanyActionFactory implements ActionFactory {
	
private final static ActionFactory factory = new CompanyActionFactory();
	
	private CompanyActionFactory() {} 
	
	public static ActionFactory getInstance() {
		return factory;
	}

	@Override
	public Action getAction(String command) {
		Action action = null;
		
		if(command.equals("/create.do")) {
			action = new CreateProductAction();
		}
		return action;
	}

	
}
