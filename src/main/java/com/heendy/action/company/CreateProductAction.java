package com.heendy.action.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.heendy.action.Action;
import com.heendy.common.ErrorCode;
import com.heendy.common.ErrorResponse;
import com.heendy.dao.ProductDAO;
import com.heendy.dto.CreateProductDTO;
import com.heendy.dto.MemberDTO;

/**
 * @author 이승준
 * 
 * 상품 생성 Action 클래스
 */
public class CreateProductAction implements Action {

	private final ProductDAO productDAO = ProductDAO.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MemberDTO member = (MemberDTO) request.getAttribute("loginUser");

		String productName = request.getParameter("productName");
		int price = Integer.parseInt(request.getParameter("price"));
		int dicountRate = Integer.parseInt(request.getParameter("discountRate"));
		int count = Integer.parseInt(request.getParameter("count"));
		String imageUrl = request.getParameter("imageUrl");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));

		try {

			CreateProductDTO createProductDTO = new CreateProductDTO(member.getMemberId(), productName, price, dicountRate, count,
					imageUrl, categoryId);

			productDAO.createProduct(createProductDTO);

			response.setStatus(201);
			response.getWriter().write("{\"created\" : true, \"result\" : \"상품이 추가되었습니다.\"}");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error message: " + e.getMessage());
			
			ErrorResponse errorResponse;
			errorResponse = ErrorResponse.of(ErrorCode.SERVER_CHECK_TIME);

			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);
		}

	}

}
