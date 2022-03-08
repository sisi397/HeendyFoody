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
import com.heendy.dto.cart.CreateCartDTO;

public class CreateCartAction implements Action {

	private final CartDAO cartDAO = CartDAO.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			int memberId = 6;
			
			int productId = Integer.parseInt(request.getParameter("product_id"));
			int companyId = Integer.parseInt(request.getParameter("company_id"));
			int count = Integer.parseInt(request.getParameter("count"));
			
			CreateCartDTO data = new CreateCartDTO(productId, companyId, memberId, count);
			this.cartDAO.createCart(data);
			
			response.setStatus(201);
			response.getWriter().write("{\"created\" : true, \"result\" :장바구니에 상품이 추가되었습니다.}");
			
		} catch(SQLException e) {
			int errorCode = e.getErrorCode();
			ErrorResponse errorResponse;
			if(errorCode == SQLErrorCode.ALREADY_CART_EXIST.getCode()) {
				errorResponse = ErrorResponse.of(ErrorCode.ALREADY_CART_EXIST);
			} else {
				errorResponse = ErrorResponse.of(ErrorCode.UNCAUGHT_SERVER_ERROR);
			}
			System.out.println(errorResponse.getMessage());
			
			String url =  request.getScheme() + "://" +   // "http" + "://
		             request.getServerName() +       // "myhost"
		             ":" + request.getServerPort() + // ":" + "8080"
		             request.getContextPath() +
		             "/cart/addCount.do";
	
			
			System.out.println(url);
			
			errorResponse.setLocation(url);
			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);		
		}
		
	}

}
