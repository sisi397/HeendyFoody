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

/**
 * @author 김시은
 * 
 * 메뉴에 따라 상품 리스트를 불러오는 Action 클래스
 * 
 */
public class ProductListAction implements Action {

	private final ProductDAO productDAO = ProductDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		try {
			// 파라미터 정보 가져오기
			String beginRowStr = request.getParameter("beginRow");
			String endRowStr = request.getParameter("endRow");
			String sort = request.getParameter("sort"); // 정렬기준
			String menu = request.getParameter("menu"); // 메뉴정보
			String pno = request.getParameter("pno"); // 페이지 정보
			
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
			
			// 화면에 뿌려줄 페이지 열번호 구하기
			int totalCount = productDAO.totalCountProduct(menu, cate, pcate);
			int pageNumber = 1;
			int listPerPage = 20;
			
			// 페이지 번호 가져오기. pno가 없을 경우 = 1
			if(pno != null && pno.length() > 0)
				pageNumber = Integer.parseInt(pno);
			
			// 시작, 끝 열번호 구하기.
			int beginRow = 0;
			int endRow = 0;
			if(beginRowStr != null && endRowStr != null) {
				beginRow = Integer.parseInt(beginRowStr);
				endRow = Integer.parseInt(endRowStr);
			}else {
				// 불러올 상품의 시작 번호, 끝 번호
				beginRow = (pageNumber - 1) * listPerPage + 1;
				endRow = beginRow + listPerPage - 1;
				if(endRow > totalCount)
					endRow = totalCount;
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
			
			String json = new Gson().toJson(productList);
			response.getWriter().write(json);
			
		} catch (SQLException e){
			ErrorResponse errorResponse;
			
			errorResponse = ErrorResponse.of(ErrorCode.UNCAUGHT_SERVER_ERROR);
			
			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);
		}
	}

}
