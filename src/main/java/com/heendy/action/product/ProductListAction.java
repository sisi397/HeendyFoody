package com.heendy.action.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.heendy.action.Action;
import com.heendy.dao.ProductDAO;
import com.heendy.dto.CategoryDTO;
import com.heendy.dto.PageDTO;
import com.heendy.dto.ProductDTO;

/**
 * @author 김시은
 * 
 * 상품 리스트를 가져오는 Action 클래스
 * */
public class ProductListAction implements Action{

	private final ProductDAO productDAO = ProductDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			// 파라미터 정보 가져오기
			String pno = request.getParameter("pno");
			String sort = request.getParameter("sort");
			String menu = request.getParameter("menu");
			String cateStr = request.getParameter("cate");
			String pcateStr = request.getParameter("pcate");
			
			// 카테고리 정보 
			int cate = 0;
			int pcate = 0;
			if(cateStr != null && pcateStr != null) {
				cate = Integer.parseInt(cateStr);
				pcate = Integer.parseInt(pcateStr);
			}

			// 정렬 기준 
			if(sort == null || sort.length() == 0)
				sort = "product_reg_date desc";
			else if(sort.equals("A"))
				sort = "product_reg_date"; //신상품순
			else if(sort.equals("B"))
				sort = "like_count desc"; //인기상품 순
			else if(sort.equals("C"))
				sort = "discount_price"; //낮은가격 순
			else if(sort.equals("D"))
				sort = "discount_price desc"; //높은가격 순
			
			/*
			 * 페이징 시작
			 * pageNumber : 현재 페이지 번호
			 * pagePerList : 한번에 보여줄 페이지 수
			 * listPerPaeg : 한 페이지 당 보여줄 상품 수
			 * totalPage : 전체 페이지 수
			 * totalCount : 전체상품 수
			 * beginPageNumber : 시작 페이지 번호
			 * endPageNumber : 끝 페이지 번호
			 */
			PageDTO pageInfo = new PageDTO();
			
			int totalCount = productDAO.totalCountProduct(menu);
			
			int pageNumber = 1;
			int pagePerList = 5;
			int listPerPage = 20;
			int totalPage = totalCount % listPerPage == 1 ? totalCount/listPerPage : totalCount/listPerPage + 1;

			// 페이지 번호 가져오기. pno가 없을 경우 = 1
			if(pno != null && pno.length() > 0)
				pageNumber = Integer.parseInt(pno);
			
			// 불러올 상품의 시작 번호, 끝 번호
			int beginRow = (pageNumber - 1) * listPerPage + 1;
			int endRow = beginRow + listPerPage - 1;
			if(endRow > totalCount)
				endRow = totalCount;
			
			// 페이지 시작 번호, 끝 번호
			int beginPageNumber = (pageNumber - 1)/pagePerList*pagePerList + 1;
			int endPageNumber = beginPageNumber + pagePerList - 1;
			if(endPageNumber > totalPage)
				endPageNumber = totalPage;
			
			pageInfo.setBeginPageNumber(beginPageNumber);
			pageInfo.setEndPageNumber(endPageNumber);
			pageInfo.setPagePerList(pagePerList);
			pageInfo.setTotalPage(totalPage);
			
			// 상품 LIST 가져오기
			ArrayList<ProductDTO> productList = productDAO.listProduct(beginRow, endRow, sort, menu, cate, pcate);
			
			
			// category 메뉴이면 : category 정보 가져오기
			if(menu.equals("category")) {
				CategoryDTO category = productDAO.listCategory(pcate);
				request.setAttribute("categoryDTO", category);
			}
			
			request.setAttribute("productList", productList);
			request.setAttribute("pageInfo", pageInfo);
			
			String url = "/pages/product/productList.jsp";
		    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		    dispatcher.forward(request, response);
		} catch(Exception e) {
			
		}
	}

}
