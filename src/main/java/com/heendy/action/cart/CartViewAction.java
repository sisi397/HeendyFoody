package com.heendy.action.cart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;

public class CartViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String viewUrl = "/pages/cart/shoppingCartList.jsp";
		
		System.out.println(request.getRequestURI());
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewUrl);
		dispatcher.forward(request, response);
		
	}
	
	

}
