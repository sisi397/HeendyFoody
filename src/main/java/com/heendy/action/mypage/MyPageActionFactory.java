package com.heendy.action.mypage;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;
import com.heendy.action.error.NotFoundViewAction;

/**
 * @author 이지민
 * mypage 관련 Action객체 생성을 위한 팩토리 클래스
 */
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

		if (command.equals("/info.do")) { //마이페이지 첫 화면
			action = new MyPageAction();
		} else if (command.equals("/order_list.do")) { //주문내역조회
			action = new OrderListAction();
		} else if (command.equals("/wish.do")) { //좋아요한 상품내역조회
			action = new WishAction();
		} else if (command.equals("/recent_view.do")) { //최근 본 상품내역조회
			action = new RecentViewAction();
		} else {
			action = new NotFoundViewAction();
		}

		return action;
	}

}
