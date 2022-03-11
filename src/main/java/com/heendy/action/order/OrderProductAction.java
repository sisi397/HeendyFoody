package com.heendy.action.order;

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
import com.heendy.dao.OrderDAO;
import com.heendy.dto.order.CreateOrderDTO;

public class OrderProductAction implements Action {

	private final OrderDAO orderDAO = OrderDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");

			/* 테스트용 멤버 id */
			int memberId = 6;
			
			int proudctId = Integer.parseInt(request.getParameter("product_id"));
			int companyId = Integer.parseInt(request.getParameter("company_id"));
			int count = Integer.parseInt(request.getParameter("product_count"));
			
			CreateOrderDTO createOrderDTO = new CreateOrderDTO(proudctId, companyId, memberId, count);
			
			orderDAO.createOrder(createOrderDTO);
			
			response.setStatus(201);
			response.getWriter().write("{\"created\" : true, \"result\" : \"주문이 완료 되었습니다.\"}");
			
		}catch(SQLException e) {
			int errorCode = e.getErrorCode();
			ErrorResponse errorResponse;
			if(errorCode == SQLErrorCode.NO_DATA_FOUND.getCode()) {
				errorResponse = ErrorResponse.of(ErrorCode.NO_DATA_FOUND);
			} else if(errorCode == SQLErrorCode.LACK_OF_STOCK.getCode()) {
				errorResponse = ErrorResponse.of(ErrorCode.LACK_OF_STOCK);
			} else {
				System.out.println(e.getMessage());
				errorResponse = ErrorResponse.of(ErrorCode.UNCAUGHT_SERVER_ERROR);
			}
			
			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);
		}
		
	}
	
	

}
