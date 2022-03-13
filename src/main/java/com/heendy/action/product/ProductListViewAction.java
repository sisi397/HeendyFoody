package com.heendy.action.product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.heendy.action.Action;
import com.heendy.common.ErrorCode;
import com.heendy.common.ErrorResponse;
import com.heendy.dao.CategoryDAO;
import com.heendy.dao.ProductDAO;
import com.heendy.dto.CategoryDTO;
import com.heendy.dto.ProductDTO;

public class ProductListViewAction implements Action {

	private final ProductDAO productDAO = ProductDAO.getInstance();
	private final CategoryDAO categoryDAO = CategoryDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		try {
			// 파라미터 정보 가져오기
			int beginRow = Integer.parseInt(request.getParameter("beginRow"));
			int endRow = Integer.parseInt(request.getParameter("endRow"));
			String sort = request.getParameter("sort");
			String menu = request.getParameter("menu");

			// 카테고리 정보
			int cate = 0;
			int pcate = 0;
			if(menu.equals("category")) {
				String cateStr = request.getParameter("cate");
				String pcateStr = request.getParameter("pcate");
				if(cateStr != null && pcateStr != null) {
					cate = Integer.parseInt(cateStr);
					pcate = Integer.parseInt(pcateStr);
				}
			}
			// 정렬 기준
			if(sort == null || sort.length() == 0)
				sort = "product_reg_date desc"; // 기본 정렬 : 신상품 순
			else if(sort.equals("A"))
				sort = "product_reg_date desc"; // 신상품순
			else if(sort.equals("B"))
				sort = "like_count desc"; // 좋아요 순
			else if(sort.equals("C"))
				sort = "discount_price"; // 낮은 가격 순
			else if(sort.equals("D"))
				sort = "discount_price desc"; // 높은 가격 순
			else if(sort.equals("E"))
				sort = "discount_rate desc"; // 할인율 큰 순
			
			// 상품 LIST 가져오기
			ArrayList<ProductDTO> productList = productDAO.listProduct(beginRow, endRow, sort, menu, cate, pcate);
			
			String json2 = "";
			// category 메뉴이면 : category 정보 가져오기
			if(menu.equals("category")) {
				ArrayList<CategoryDTO> category = categoryDAO.listCategory(cate, pcate);
				request.setAttribute("categoryList", category);
				json2 = new Gson().toJson(category);
			}
			
			String json = new Gson().toJson(productList);
			response.getWriter().write(json);
		} catch (SQLException e){
			int errorCode = e.getErrorCode();
			ErrorResponse errorResponse;
			
			errorResponse = ErrorResponse.of(ErrorCode.UNCAUGHT_SERVER_ERROR);
			
			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);
		}
	}

}
