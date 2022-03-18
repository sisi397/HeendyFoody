package com.heendy.action.order;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;
import com.heendy.action.error.NotFoundViewAction;

/**
 * @author 이승준
 * 
 * order 관련 Action 객체 생성을 위한 팩토리 클래스
 * */
public class OrderActionFactory implements ActionFactory{

private final static ActionFactory factory = new OrderActionFactory();
	
	private OrderActionFactory() {} 
	

	
	public static ActionFactory getInstance() {
		return factory;
	}
	
	@Override
	public Action getAction(String command) {
		Action action = null;
		
		if(command.equals("/orderProduct.do")) {
			action = new OrderProductProxyAction(new OrderProductAction());
		} else if (command.equals("/orderCartProducts.do")) {
			action = new OrderCartProductsProxyAction(new OrderCartProductsAction());
		} else {
			action = new NotFoundViewAction();
		}
		
		return action;
	}

	
}
