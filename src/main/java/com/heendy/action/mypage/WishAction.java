package com.heendy.action.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.dao.WishDAO;
import com.heendy.dto.WishDTO;

public class WishAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/pages/mypage/wish.jsp";
		
		//로그인 여부 분기처리 필요
		
		String pno = request.getParameter("pno");
		WishDAO wishDAO = WishDAO.getInstance();
		
		int totalCount = wishDAO.totalWishCount(6); //전체 좋아요 상품 수
		System.out.println("totalCount: " + totalCount);
		int pageNumber = 1; // 현재 페이지 번호
		int pagePerList = 5; // 보여줄 페이지 수
		int listPerPage = 10; // 한 페이지 당 보여줄 좋아요 상품 수


		// 페이지 번호 계산
		if (pno == null || pno.length() == 0) {
			pageNumber = 1;
		}
		try {
			pageNumber = Integer.parseInt(pno);
		} 
		catch(NumberFormatException e) {
			pageNumber = 1;
		}
				
		int beginRow = (pageNumber - 1) * listPerPage + 1;
		int endRow = beginRow + listPerPage - 1;
		if(endRow > totalCount) {
			endRow = totalCount;
		}
		
		
	    ArrayList<WishDTO> wishList = wishDAO.listWish(beginRow, endRow, 6);
	    
		int beginPageNumber = (pageNumber - 1) / pagePerList * pagePerList + 1;
		int endPageNumber = beginPageNumber + pagePerList - 1;
	    int totalPage = (totalCount - 1) / listPerPage + 1; // 총 페이지 수
	    System.out.println("totalpage : " + totalPage);
	    if (totalPage < endPageNumber) {
	    	endPageNumber = totalPage;
	    }	    

	    
	    request.setAttribute("beginPage", beginPageNumber);
	    request.setAttribute("endPage", endPageNumber);
	    request.setAttribute("pagePerList", pagePerList);
	    request.setAttribute("totalPageCount", totalPage);
	    
	    request.setAttribute("wishList", wishList);
	    request.getRequestDispatcher(url).forward(request, response);	

	}

}
