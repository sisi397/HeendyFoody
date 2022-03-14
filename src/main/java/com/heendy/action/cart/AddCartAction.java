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
import com.heendy.common.SQLErrorCode;
import com.heendy.common.ValidableAction;
import com.heendy.dao.CartDAO;
import com.heendy.dto.cart.CartCountUpdateDTO;
import com.heendy.utils.Validation;

/**
 * @author 이승준 장바구니 수량 증가 Action 클래스
 */
public class AddCartAction implements Action,ValidableAction {

	private final CartDAO cartDAO = CartDAO.getInstance();


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		int memberId = (int) request.getAttribute("memberId");
		
		try {
			
			List<ErrorResponse.ErrorField> errors = valid(request);
			System.out.println(errors.size());
			if(errors.size() != 0) {
				ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_FIELDS);
				
				errorResponse.setErrors(errors);
				
				String json = new Gson().toJson(errorResponse);
				response.setStatus(errorResponse.getStatus());
				response.getWriter().write(json);
				return;
			}
		
			int cartId = Integer.parseInt(request.getParameter("cart_id"));
			int count = Integer.parseInt(request.getParameter("count"));

			CartCountUpdateDTO addCartDto = new CartCountUpdateDTO(cartId, memberId, count);

			cartDAO.addCartCount(addCartDto);

			response.setStatus(201);
			response.getWriter().write("{\"updated\" : true, \"result\" : \"장바구니의 수량이 추가되었습니다.\"}");

		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			System.out.println(errorCode);
			ErrorResponse errorResponse;
			if (errorCode == SQLErrorCode.LACK_OF_STOCK.getCode()) {
				errorResponse = ErrorResponse.of(ErrorCode.LACK_OF_STOCK);
			} else if (errorCode == SQLErrorCode.NO_DATA_FOUND.getCode()) {
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
	public List<ErrorResponse.ErrorField> valid(HttpServletRequest request) {
		List<ErrorResponse.ErrorField> errors = new ArrayList<>();
		
		String cartId = request.getParameter("cart_id");
		String count = request.getParameter("count");
		
		System.out.println(request.getParameter("cart_id"));
		System.out.println(request.getParameter("count"));
		
		Validation validation = Validation.getInstance();
		
		if(!validation.validNotEmpty(cartId)) {
			errors.add(new ErrorResponse.ErrorField("cart_id",cartId,"값이 비어있습니다."));
		}
		
		if(!validation.validNotEmpty(count)) {
			errors.add(new ErrorResponse.ErrorField("count",count,"값이 비어있습니다."));
		}
		
		return errors;
	}

}
