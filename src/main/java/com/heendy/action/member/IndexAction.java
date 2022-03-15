package com.heendy.action.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.dao.ProductDAO;
import com.heendy.dto.ProductDTO;

public class IndexAction implements Action {

	private final ProductDAO productDAO = ProductDAO.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  		
		try {

			// 세일상품 가져오기
			List<ProductDTO> saleProduct = productDAO.listProduct(1,1,"discount_rate desc","sale",0,0);
			request.setAttribute("saleProduct", saleProduct);
			
			// 좋아요 많은 상품 가져오기
			List<ProductDTO> bestProduct = productDAO.listProduct(1,3,"like_count desc","best",0,0);
			request.setAttribute("bestProduct", bestProduct);
			
			// 신상품 가져오기
			List<ProductDTO> newProduct = productDAO.listProduct(1,4,"product_reg_date desc","newprod",0,0);
			request.setAttribute("newProduct", newProduct);
			
			String url = "/index.jsp";

		    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		    dispatcher.forward(request, response);
		}catch(Exception e){
			e.getStackTrace();
		}
	  	
	}

}
