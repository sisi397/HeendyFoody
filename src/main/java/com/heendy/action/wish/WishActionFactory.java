package com.heendy.action.wish;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;
import com.heendy.action.error.NotFoundViewAction;

public class WishActionFactory implements ActionFactory {

	private final static ActionFactory factory = new WishActionFactory();
	
	private WishActionFactory() {} 
	
	public static ActionFactory getInstance() {
		return factory;
	}
	
	@Override
	public Action getAction(String command) {
		Action action = null;
	    System.out.println("WishActionFactory  :" + command);

	    if (command.equals("/insert.do")) {
	      action = new WishInsertAction();
	    } else if(command.equals("/delete.do")) {
	    	action = new WishDeleteAction();
	    } else if(command.equals("/check.do")) {
	    	action = new WishCheckAction();
	    } else {
			action = new NotFoundViewAction();
		}
	    
		return action;
	}

}
