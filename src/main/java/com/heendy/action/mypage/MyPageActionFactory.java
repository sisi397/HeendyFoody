package com.heendy.action.mypage;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;
import com.heendy.action.error.NotFoundViewAction;

public class MyPageActionFactory implements ActionFactory {

	private final static ActionFactory factory = new MyPageActionFactory();

	private MyPageActionFactory() {
	}

	public static ActionFactory getInstance() {
		return factory;
	}

	@Override
	public Action getAction(String command) {
		Action action = null;
		System.out.println("MyPageActionFactory  :" + command);

		if (command.equals("/info.do")) {
			action = new MyPageAction();
		} else if (command.equals("/order_list.do")) {
			action = new OrderListAction();
		} else if (command.equals("/wish.do")) {
			action = new WishAction();
		} else if (command.equals("/recent_view.do")) {
			action = new RecentViewAction();
		} else {
			action = new NotFoundViewAction();
		}

		return action;
	}

}
