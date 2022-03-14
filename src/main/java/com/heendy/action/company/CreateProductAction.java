package com.heendy.action.company;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.heendy.action.Action;
import com.heendy.common.ErrorCode;
import com.heendy.common.ErrorResponse;
import com.heendy.dao.ProductDAO;
import com.heendy.dto.CreateProductDTO;

public class CreateProductAction implements Action {

	private final ProductDAO productDAO = ProductDAO.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		/* test 업체 아이디 */
		int companyId = 1;

		String productName = request.getParameter("productName");
		int price = Integer.parseInt(request.getParameter("price"));
		int dicountRate = Integer.parseInt(request.getParameter("discountRate"));
		int count = Integer.parseInt(request.getParameter("count"));
		String imageUrl = request.getParameter("imageUrl");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));

		try {

			CreateProductDTO createProductDTO = new CreateProductDTO(companyId, productName, price, dicountRate, count,
					imageUrl, categoryId);

			productDAO.createProduct(createProductDTO);
			
			response.setStatus(201);
			response.getWriter().write("{\"created\" : true, \"result\" : \"상품이 추가되었습니다.\"}");

		} catch (Exception e) {
			e.printStackTrace();
			ErrorResponse errorResponse;
			errorResponse = ErrorResponse.of(ErrorCode.UNCAUGHT_SERVER_ERROR);

			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);
		}

	}

}
