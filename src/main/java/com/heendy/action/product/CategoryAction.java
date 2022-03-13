package com.heendy.action.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.heendy.action.Action;
import com.heendy.dao.CategoryDAO;
import com.heendy.dto.CategoryDTO;

public class CategoryAction implements Action {

	private final CategoryDAO categoryDAO = CategoryDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		int cate = Integer.parseInt(request.getParameter("cate"));
		int pcate = Integer.parseInt(request.getParameter("pcate"));
		
		ArrayList<CategoryDTO> category = categoryDAO.listCategory(cate, pcate);
		request.setAttribute("categoryList", category);
		String json = new Gson().toJson(category);
		response.getWriter().write(json);
	}

}
