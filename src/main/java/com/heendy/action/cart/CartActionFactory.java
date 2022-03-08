package com.heendy.action.cart;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;

public class CartActionFactory implements ActionFactory{

	private final static ActionFactory factory = new CartActionFactory();
	
	private CartActionFactory() {} 
	
	public static ActionFactory getInstance() {
		return factory;
	}
	
	@Override
	public Action getAction(String command) {
		Action action = null;
		System.out.println(command);
		if(command.equals("/create.do")) {
			action = new CreateCartAction();
		} else if (command.equals("/shoppingCartList.do")) {
			action = new CartViewAction();
		}
	
		return action;
	}
	
	 

}
