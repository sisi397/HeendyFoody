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
import com.heendy.common.ErrorCode;
import com.heendy.common.ErrorResponse;
import com.heendy.dao.CategoryDAO;
import com.heendy.dao.ProductDAO;
import com.heendy.dto.CategoryDTO;
import com.heendy.dto.PageDTO;
import com.heendy.dto.ProductDTO;

/**
 * @author 김시은
 * 
 * 상품 목록으로 페이지 이동하는 Action 클래스
 * 
 * */
public class ProductListViewAction implements Action{

	private final CategoryDAO categoryDAO = CategoryDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		try {
			// 파라미터 정보 가져오기
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
			
			// category 메뉴이면 : category 정보 가져오기
			if(menu.equals("category")) {
				ArrayList<CategoryDTO> category = categoryDAO.listCategory(cate, pcate);
				request.setAttribute("categoryList", category);
			}
			
			String url = "/pages/product/productList.jsp";
		    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		    dispatcher.forward(request, response);
		}  catch (SQLException e){
			ErrorResponse errorResponse;
			
			errorResponse = ErrorResponse.of(ErrorCode.UNCAUGHT_SERVER_ERROR);
			
			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);
		}
	}

}
