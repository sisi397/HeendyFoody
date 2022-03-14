package com.heendy.action.product;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.heendy.action.Action;
import com.heendy.common.ErrorCode;
import com.heendy.common.ErrorResponse;
import com.heendy.dao.ProductDAO;
import com.heendy.dto.PageDTO;

/**
 * 
 * @author 김시은
 * 
 * 페이지 정보를 구하는 Action 클래스
 * 
 */
public class PaginationAction implements Action {

	private final ProductDAO productDAO = ProductDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 파라미터 정보 가져오기
			String pno = request.getParameter("pno");  // 현재 페이지 번호
			String menu = request.getParameter("menu"); // 현재 페이지 메뉴 정보

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
			
			/**
			 * 페이징 시작
			 * pageNumber : 현재 페이지 번호
			 * pagePerList : 한번에 보여줄 페이지 수
			 * listPerPaeg : 한 페이지 당 보여줄 상품 수
			 * totalPage : 전체 페이지 수
			 * totalCount : 전체상품 수
			 * beginPageNumber : 시작 페이지 번호
			 * endPageNumber : 끝 페이지 번호
			 */
			
			// 메뉴에 따라 전체 상품 개수를 가져온다.
			// category 메뉴일 경우 categoryId, parentCategoryId가 필요
			int totalCount = productDAO.totalCountProduct(menu, cate, pcate);
			int pageNumber = 1;
			int pagePerList = 5;
			int listPerPage = 20;
			int totalPage = totalCount % listPerPage == 1 ? totalCount/listPerPage : totalCount/listPerPage + 1;

			// 페이지 번호 가져오기. pno가 없을 경우 = 1
			if(pno != null && pno.length() > 0)
				pageNumber = Integer.parseInt(pno);
			
			// 페이지 시작 번호, 끝 번호
			int beginPageNumber = (pageNumber - 1)/pagePerList*pagePerList + 1;
			int endPageNumber = beginPageNumber + pagePerList - 1;
			if(endPageNumber > totalPage)
				endPageNumber = totalPage;

			PageDTO pageInfo = new PageDTO(beginPageNumber, endPageNumber, pagePerList, totalPage);

			String json = new Gson().toJson(pageInfo);
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
