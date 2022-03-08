package com.heendy.action.wish;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.dao.ProductDAO;
import com.heendy.dao.WishDAO;
import com.heendy.dto.ProductDTO;

public class WishInsertAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mid = Integer.parseInt(request.getParameter("memberId"));
		int pid = Integer.parseInt(request.getParameter("productId"));
		int cid = Integer.parseInt(request.getParameter("companyId"));
		System.out.println(mid);
		// 좋아요 추가
		WishDAO wishDAO = WishDAO.getInstance();
		int wishInsert = wishDAO.insertWish(mid, pid, cid);
		
		if(wishInsert == 1) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
		
//		String url = "pages/product/productDetail.jsp";
//	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//	    dispatcher.forward(request, response);
	}

}
