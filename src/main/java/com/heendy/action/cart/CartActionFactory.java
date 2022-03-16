package com.heendy.action.cart;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;
import com.heendy.action.error.NotFoundViewAction;

/**
 * @author 이승준
 * cart 관련 Action 객체 생성을 위한 팩토리 클래스
 * */
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
			action = new CreateCartProxyAction(new CreateCartAction());
		} else if (command.equals("/shoppingCartList.do")) {
			action = new CartViewAction();
		} else if (command.equals("/addCount.do")) {
			action = new AddCartProxyAction(new AddCartAction());
		} else if (command.equals("/minusCount.do")) {
			action = new MinusCartProxyAction(new MinusCartAction());
		} else if (command.equals("/delete.do")) {
			action = new DeleteCartProxyAction(new DeleteCartAction());
		} else {
			action = new NotFoundViewAction();
		}
	
		return action;
	}
	
	 

}
