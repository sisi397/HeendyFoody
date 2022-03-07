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
		// TODO Auto-generated method stub
	
		return null;
	}
	
	 

}
