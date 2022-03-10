package com.heendy.action.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.dao.ProductDAO;
import com.heendy.dto.ProductDTO;

/** 
 * 
 * @author 김시은
 * 
 * 상품 상세보기 페이지로 이동
 *
 */
public class ProductDetailViewAction implements Action {

	private final ProductDAO productDAO = ProductDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pid = Integer.parseInt(request.getParameter("pid"));

		// 상품 detail 가져오기
		// dao에서
		ProductDTO product = productDAO.detailProduct(pid);
		request.setAttribute("product", product);
		
		String url = "/pages/product/productDetail.jsp";
		
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}

}
