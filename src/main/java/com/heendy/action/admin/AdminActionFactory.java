package com.heendy.action.admin;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;

public class AdminActionFactory implements ActionFactory {
	
	private final static ActionFactory factory = new AdminActionFactory();
	
	private AdminActionFactory() {} 
	
	public static ActionFactory getInstance() {
		return factory;
	}
	
	@Override
	public Action getAction(String command) {
		Action action = null;
		
	    System.out.println("ActionFactory  :" + command);

	    if (command.equals("/ageinfoChart.do")) {
	    	action = new AgeInfoAction();
	    }
	    
		return action;
	}

}
