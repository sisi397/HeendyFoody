package com.heendy.action.wish;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;

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

	    if (command.equals("/insert")) {
	      action = new WishInsertAction();
	    } else if(command.equals("/delete")) {
	    	action = new WishDeleteAction();
	    }
	    
		return action;
	}

}
