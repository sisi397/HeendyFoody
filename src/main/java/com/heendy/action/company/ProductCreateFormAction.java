package com.heendy.action.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.dao.CategoryDAO;
import com.heendy.dto.CategoryDTO;

public class ProductCreateFormAction implements Action {

	private final CategoryDAO categoryDAO = CategoryDAO.getInstance();

	private final String VIEW_URL = "/pages/company/productCreateForm.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			List<CategoryDTO> categoryList = categoryDAO.listCategory(0, 0);
			
			System.out.println(categoryList.size());

			HashMap<Integer, List<CategoryDTO>> categoryMap = new HashMap<>();

			/* 자신의 카테고리 id와 부모 카테고리 id가 같은 카테고리 대분류 */
			for (CategoryDTO category : categoryList) {
				int categoryId = category.getCategoryId();
				int pCategoryId = category.getParentCategoryId();

				if (categoryId == pCategoryId) {
					List<CategoryDTO> categories = new ArrayList<>();
					categories.add(category);
					categoryMap.put(categoryId, categories);
				}
			}

			/* 자신의 부모 카테고리로 분류 */
			for (CategoryDTO category : categoryList) {
				int categoryId = category.getCategoryId();
				int pCategoryId = category.getParentCategoryId();

				if (categoryId != pCategoryId) {
					categoryMap.get(pCategoryId).add(category);
				}
			}
			
			for(Entry<Integer, List<CategoryDTO>> entry: categoryMap.entrySet()) {
				System.out.println("대분류 id : " + entry.getKey());
				for(CategoryDTO c: entry.getValue()) {
					System.out.println(c.getCategoryId());
					System.out.println(c.getCategoryName());
				}
			}

			request.setAttribute("categoryList", categoryMap);
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_URL);
		dispatcher.forward(request, response);

	}

}
