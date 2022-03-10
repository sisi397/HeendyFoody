package com.heendy.action.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.heendy.action.Action;
//import com.heendy.dto.MemberDTO;
//import com.heendy.dao.WishDAO;
import com.heendy.dao.WishDAO;
import com.heendy.dto.WishDTO;

public class MyPageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String url = "/pages/mypage/mypage.jsp"; 
		
//	    HttpSession session = request.getSession();
//	    MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

//	    if (loginUser == null) {
//	        url = "/pages/login_form";
//	      } 
//	    else {
//			WishDAO wishDAO = WishDAO.getInstance();
//	    	
//	    }
		
		WishDAO wishDAO = WishDAO.getInstance();
		int totalCount = wishDAO.totalWishCount(6);
		
		int beginRow = 1;
		int endRow = 5;
		if(endRow > totalCount) {
			endRow = totalCount;
		}
		
		ArrayList<WishDTO> wishList = wishDAO.listWish(beginRow, endRow, 6);
		
	    request.setAttribute("totalCount", totalCount);
	    request.setAttribute("wishList", wishList);
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
