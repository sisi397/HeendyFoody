package com.heendy.action.cart;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.heendy.action.Action;
import com.heendy.common.ErrorCode;
import com.heendy.common.ErrorResponse;
import com.heendy.common.ErrorResponse.ErrorField;
import com.heendy.common.SQLErrorCode;
import com.heendy.common.ValidableAction;
import com.heendy.dao.CartDAO;
import com.heendy.utils.Validation;

/**
 * @author 이승준 자신의 장바구니 목록 중에서 장바구니 아이템을 삭제하는 Action 클래스
 */
public class DeleteCartAction implements Action, ValidableAction {

	private final CartDAO cartDAO = CartDAO.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		int memberId = (int) request.getAttribute("memberId");

		List<ErrorResponse.ErrorField> errors = valid(request);
		System.out.println(errors.size());
		if (errors.size() != 0) {
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_FIELDS);

			errorResponse.setErrors(errors);

			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);
			return;
		}

		try {
			int cartId = Integer.parseInt(request.getParameter("cart_id"));

			cartDAO.deleteCartByCartIdAndMemberId(cartId, memberId);

			response.setStatus(201);
			response.getWriter().write(
					"{\"deleted\" : true, \"deleted_cart_id\" : " + cartId + ", \"result\" : \"장바구니 아이템이 삭제되었습니다.\"}");

		} catch (SQLException e) {
			int errorCode = e.getErrorCode();

			ErrorResponse errorResponse;
			if (errorCode == SQLErrorCode.NO_DATA_FOUND.getCode()) {
				errorResponse = ErrorResponse.of(ErrorCode.NO_DATA_FOUND);
			} else if (errorCode == SQLErrorCode.NOT_RESOURCE_OWNER.getCode()) {
				errorResponse = ErrorResponse.of(ErrorCode.NOT_RESOURCE_OWNER);
			} else {
				errorResponse = ErrorResponse.of(ErrorCode.UNCAUGHT_SERVER_ERROR);
			}

			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);
		}

	}

	@Override
	public List<ErrorField> valid(HttpServletRequest request) {
		List<ErrorResponse.ErrorField> errors = new ArrayList<>();

		String cartId = request.getParameter("cart_id");

		Validation validation = Validation.getInstance();

		if (!validation.validNotEmpty(cartId)) {
			errors.add(new ErrorResponse.ErrorField("cart_id", cartId, "값이 비어있습니다."));
		}

		return errors;
	}

}
