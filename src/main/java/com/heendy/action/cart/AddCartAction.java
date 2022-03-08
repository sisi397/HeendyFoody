package com.heendy.action.cart;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.heendy.action.Action;
import com.heendy.common.ErrorCode;
import com.heendy.common.ErrorResponse;
import com.heendy.common.SQLErrorCode;
import com.heendy.dao.CartDAO;
import com.heendy.dto.cart.AddCartDTO;

public class AddCartAction implements Action {

	private final CartDAO cartDAO = CartDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		/*테스트용*/
		int memberId = 6;
		
		try {
			int cartId = Integer.parseInt(request.getParameter("cart_id"));
			int count =Integer.parseInt(request.getParameter("count"));
			
			AddCartDTO addCartDto = new AddCartDTO(cartId, count);
			
			cartDAO.addCart(addCartDto);
			
			response.setStatus(201);
			response.getWriter().write("{\"updated\" : true, \"result\" :장바구니의 수량이 추가되었습니다.}");
			
		}catch(SQLException e) {
			int errorCode = e.getErrorCode();
			ErrorResponse errorResponse;
			if(errorCode == SQLErrorCode.LACK_OF_STOCK.getCode()) {
				errorResponse = ErrorResponse.of(ErrorCode.LACK_OF_STOCK);
			} else {
				errorResponse = ErrorResponse.of(ErrorCode.UNCAUGHT_SERVER_ERROR);
			}
			
			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);	
		}
		
	}
	
	

}
