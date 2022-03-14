package com.heendy.action.company;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.heendy.common.ErrorResponse.ErrorField;
import com.heendy.utils.Validation;
import com.heendy.action.Action;
import com.heendy.common.ErrorResponse;
import com.heendy.common.ValidRequireAction;

public class CreateProductProxyAction extends ValidRequireAction {

	public CreateProductProxyAction(Action action) {
		super(action);
	}

	@Override
	protected List<ErrorField> valid(HttpServletRequest request) {
		List<ErrorResponse.ErrorField> errors = new ArrayList<>();

		String productName = request.getParameter("productName");
		String price = request.getParameter("price");
		String dicountRate = request.getParameter("discountRate");
		String count = request.getParameter("count");
		String imageUrl = request.getParameter("imageUrl");
		String categoryId = request.getParameter("categoryId");

		Validation validation = Validation.getInstance();

		/* 상품 이름이 비어있는지 확인 */
		if (!validation.validNotEmpty(productName)) {
			errors.add(new ErrorResponse.ErrorField("productName", productName, "값이 비어있습니다."));
		}

		/* 상품 이미지가 비어있는지 확인 */
		if (!validation.validNotEmpty(imageUrl)) {
			errors.add(new ErrorResponse.ErrorField("imageUrl", imageUrl, "값이 비어있습니다."));
		}

		/* 가격이 비어있는지 확인 */
		if (!validation.validNotEmpty(price)) {
			errors.add(new ErrorResponse.ErrorField("price", price, "값이 비어있습니다."));
		} else {
			/* 가격이 숫자인지 확인 */
			if (!validation.validIsNumber(price)) {
				errors.add(new ErrorResponse.ErrorField("price", price, "값이 비어있습니다."));
			}			
		}


		/* 상품 할인율이 비어있는지 확인 */
		if (!validation.validIsNumber(dicountRate)) {
			errors.add(new ErrorResponse.ErrorField("dicountRate", dicountRate, "값이 비어있습니다."));
		} else {
			/* 상품 할인율이 숫자인지 확인 */
			if (!validation.validIsNumber(dicountRate)) {
				errors.add(new ErrorResponse.ErrorField("dicountRate", dicountRate, "숫자만 가능합니다."));
			} else {
				int target = Integer.parseInt(dicountRate);
				
				/* 상품 할인율이 0이상인지 확인 */
				if (!validation.validMin(target, 0)) {
					errors.add(new ErrorResponse.ErrorField("dicountRate", dicountRate, "최소 0 이상만 가능합니다."));
				}
				
				/* 상품 할인율이 100이하인지 확인 */
				if (!validation.validMax(target, 100)) {
					errors.add(new ErrorResponse.ErrorField("dicountRate", dicountRate, "최대 100 이하만 가능합니다."));
				}
			}
		}


		/* 상품 수량이 비어있는지 확인 */
		if (!validation.validNotEmpty(count)) {
			errors.add(new ErrorResponse.ErrorField("count", count, "값이 비어있습니다."));
		} else {
			/* 상품 수량이 숫자인지 확인 */
			if (!validation.validIsNumber(count)) {
				errors.add(new ErrorResponse.ErrorField("count", count, "숫자만 가능합니다."));
			} else {
				int target = Integer.parseInt(count);

				/* 상품 수량이 1개 이상인지 확인 */
				if (!validation.validMin(target, 1)) {
					errors.add(new ErrorResponse.ErrorField("count", count, "최소 1 이상만 가능합니다."));
				}
			}

		}

		/* 카테고리 id가 비어있는지 확인 */
		if (!validation.validNotEmpty(categoryId)) {
			errors.add(new ErrorResponse.ErrorField("categoryId", categoryId, "값이 비어있습니다."));
		} else {
			/* 카테고리 id가 숫자인지 확인 */
			if (!validation.validIsNumber(categoryId)) {
				errors.add(new ErrorResponse.ErrorField("categoryId", categoryId, "숫자만 가능합니다."));
			}
		}

		return errors;
	}

}
