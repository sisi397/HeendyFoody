package com.heendy.action.order;

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
 * 장바구니에 담긴 상품중 선택한 물품 모두 결제 Action전 요청 파라미터 검증 Proxy 클래스
 * */
public class OrderCartProductsProxyAction extends ValidRequireAction {
	
	public OrderCartProductsProxyAction(Action action) {
		super(action);
	}

	@Override
	protected List<ErrorField> valid(HttpServletRequest request) {
		List<ErrorResponse.ErrorField> errors = new ArrayList<>();

		String[] cartIds = request.getParameterValues("itemSelect");

		Validation validation = Validation.getInstance();

		if (!validation.validNotEmpty(cartIds)) {
			errors.add(new ErrorResponse.ErrorField("itemSelect", "", "값이 비어있습니다."));
		} else {
			for(String cartId: cartIds) {
				if(!validation.validIsNumber(cartId)) {
					errors.add(new ErrorResponse.ErrorField("itemSelect", cartId, "숫자만 가능합니다."));
				}
			}
		}

		return errors;
	}
	
	

}
