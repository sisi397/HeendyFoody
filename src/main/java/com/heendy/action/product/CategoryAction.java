package com.heendy.action.product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.heendy.action.Action;
import com.heendy.common.ErrorCode;
import com.heendy.common.ErrorResponse;
import com.heendy.dao.CategoryDAO;
import com.heendy.dto.CategoryDTO;

/**
 * @author 김시은
 * 
 * 카테고리 리스트를 불러오는 Action 클래스
 * 
 */
public class CategoryAction implements Action {

	private final CategoryDAO categoryDAO = CategoryDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
		try {
			int cate = Integer.parseInt(request.getParameter("cate"));
			int pcate = Integer.parseInt(request.getParameter("pcate"));
			
			// 카테고리id에 따라 카테고리 정보를 가져온다. 
			// 전체 카테고리 불러오기 => cate : 0, pcate : 0
			ArrayList<CategoryDTO> category = categoryDAO.listCategory(cate, pcate);
			request.setAttribute("categoryList", category);
			String json = new Gson().toJson(category);
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
