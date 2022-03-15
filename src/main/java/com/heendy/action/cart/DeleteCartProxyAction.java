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
 * 자신의 장바구니 목록 중에서 장바구니 아이템을 삭제하는 Action 전 요청 파라미터 검증 Proxy 클래스
 * */
public class DeleteCartProxyAction extends ValidRequireAction {

	public DeleteCartProxyAction(Action action) {
		super(action);
	}
	
	@Override
	protected List<ErrorField> valid(HttpServletRequest request) {
		List<ErrorResponse.ErrorField> errors = new ArrayList<>();

		String cartId = request.getParameter("cart_id");

		Validation validation = Validation.getInstance();

		if (!validation.validNotEmpty(cartId)) {
			errors.add(new ErrorResponse.ErrorField("cart_id", cartId, "값이 비어있습니다."));
		} else {
			if(!validation.validIsNumber(cartId)) {
				errors.add(new ErrorResponse.ErrorField("cart_id", cartId, "숫자만 가능합니다."));
			}
		}

		return errors;
	}

	
}
