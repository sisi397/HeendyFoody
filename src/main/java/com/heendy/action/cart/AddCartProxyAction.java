package com.heendy.action.cart;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.heendy.action.Action;
import com.heendy.common.ErrorResponse.ErrorField;
import com.heendy.utils.Validation;
import com.heendy.common.ErrorResponse;
import com.heendy.common.ValidRequireAction;

/**
 * @author 이승준
 * 
 * 장바구니 수량 증가 Action 실행 전에 request 파라미터를 검증하는 Proxy 클래스
 * */
public class AddCartProxyAction extends ValidRequireAction {

	public AddCartProxyAction(Action action) {
		super(action);
	}

	/**
	 * 장바구니 id, 장바구니 수량 요청 파라미터 검증
	 * */
	@Override
	protected List<ErrorField> valid(HttpServletRequest request) {
		List<ErrorResponse.ErrorField> errors = new ArrayList<>();

		String cartId = request.getParameter("cart_id");
		String count = request.getParameter("count");

		System.out.println(request.getParameter("cart_id"));
		System.out.println(request.getParameter("count"));

		Validation validation = Validation.getInstance();

		if (!validation.validNotEmpty(cartId)) {
			errors.add(new ErrorResponse.ErrorField("cart_id", cartId, "값이 비어있습니다."));
		} else {
			if(!validation.validIsNumber(cartId)) {
				errors.add(new ErrorResponse.ErrorField("cart_id", cartId, "숫자만 가능합니다."));
			}
		}

		if (!validation.validNotEmpty(count)) {
			errors.add(new ErrorResponse.ErrorField("count", count, "값이 비어있습니다."));
		} else {
			if(!validation.validIsNumber(count)) {
				errors.add(new ErrorResponse.ErrorField("count", count, "숫자만 가능합니다."));
			}
		}

		return errors;
	}

}
