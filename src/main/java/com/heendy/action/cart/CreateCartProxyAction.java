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
 * 장바구니 생성 Action 실행 전에 request 파라미터를 검증하는 Proxy 클래스
 * */
public class CreateCartProxyAction extends ValidRequireAction {

	public CreateCartProxyAction(Action aciton) {
		super(aciton);
	}

	@Override
	protected List<ErrorField> valid(HttpServletRequest request) {
		List<ErrorResponse.ErrorField> errors = new ArrayList<>();

		String productId = request.getParameter("product_id");
		String compayId = request.getParameter("company_id");
		String count = request.getParameter("count");

		Validation validation = Validation.getInstance();

		/*상품 id 비어있는지 확인*/
		if (!validation.validNotEmpty(productId)) {
			errors.add(new ErrorResponse.ErrorField("product_id", productId, "값이 비어있습니다."));
		} else {
			if(!validation.validIsNumber(productId)) {
				errors.add(new ErrorResponse.ErrorField("product_id", productId, "숫자만 가능합니다."));
			}
		}

		/*업체 id 비어있는지 확인*/
		if (!validation.validNotEmpty(compayId)) {
			errors.add(new ErrorResponse.ErrorField("company_id", compayId, "값이 비어있습니다."));
		} else {
			if(!validation.validIsNumber(compayId))
			errors.add(new ErrorResponse.ErrorField("company_id", compayId, "숫자만 가능합니다."));
		}

		/*장바구니 수량이 비어있는지 확인*/
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
