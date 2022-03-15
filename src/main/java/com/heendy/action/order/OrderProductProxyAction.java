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
 * 상품 결제 Action 전 요청 파라미터 검증 클래스
 * */
public class OrderProductProxyAction extends ValidRequireAction {
	
	public OrderProductProxyAction(Action action) {
		super(action);
	}

	@Override
	protected List<ErrorField> valid(HttpServletRequest request) {
		List<ErrorResponse.ErrorField> errors = new ArrayList<>();

		String productId = request.getParameter("product_id");
		String compayId = request.getParameter("company_id");
		String count = request.getParameter("product_count");

		Validation validation = Validation.getInstance();

		/*상품 id 비어있는지 확인*/
		if (!validation.validNotEmpty(productId)) {
			errors.add(new ErrorResponse.ErrorField("product_id", productId, "값이 비어있습니다."));
		} else {
			/*상품 id가 숫자인지 확인*/
			if(!validation.validIsNumber(productId)) {
				errors.add(new ErrorResponse.ErrorField("product_id", productId, "숫자만 가능합니다."));
			}
		}

		if (!validation.validNotEmpty(compayId)) {
			errors.add(new ErrorResponse.ErrorField("company_id", compayId, "값이 비어있습니다."));
		} else {
			if(!validation.validIsNumber(compayId)) {
				errors.add(new ErrorResponse.ErrorField("company_id", compayId, "숫자만 가능합니다."));
			}
		}

		if (!validation.validNotEmpty(count)) {
			errors.add(new ErrorResponse.ErrorField("product_count", count, "값이 비어있습니다."));
		} else {
			if(!validation.validIsNumber(count)) {
				errors.add(new ErrorResponse.ErrorField("product_count", count, "숫자만 가능합니다."));
			}
		}
 
		return errors;
	}

	
}
