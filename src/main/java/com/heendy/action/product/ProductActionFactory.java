package com.heendy.action.product;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;
import com.heendy.action.company.CreateProductAction;

public class ProductActionFactory implements ActionFactory {
	
	private final static ActionFactory factory = new ProductActionFactory();
	
	private ProductActionFactory() {} 
	
	public static ActionFactory getInstance() {
		return factory;
	}
	
	@Override
	public Action getAction(String command) {
		Action action = null;
		
	    System.out.println("ActionFactory  :" + command);

	    if (command.equals("/list.do")) {
	    	action = new ProductListAction();
	    } else if(command.equals("/detail.do")) {
	    	action = new ProductDetailAction();
	    }
	    
		return action;
	}

}
