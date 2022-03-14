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
		
	    System.out.println("ActionFactory  :" + command);

	    if (command.equals("/ageinfoChart.do")) {
	    	action = new AgeInfoAction();
	    } else if(command.equals("/orderinfoChart.do")) {
	    	action = new OrderInfoAction();
	    } else if(command.equals("/productList.do")) {
	    	action = new ProductListAction();
	    } else if(command.equals("/company.do")) {
	    	action = new CompanyPageViewAction();
	    } else if(command.equals("/createProduct.do")) {
			action = new CreateProductProxyAction(new CreateProductAction());
		} else if(command.equals("/createProductForm.do")) {
			action = new ProductCreateFormAction();
		}
	    
		return action;
	}
}
