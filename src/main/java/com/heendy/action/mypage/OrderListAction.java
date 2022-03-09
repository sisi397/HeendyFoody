package com.heendy.action.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.heendy.action.Action;
import com.heendy.dto.OrderDTO;
import com.heendy.dao.OrderDAO;

public class OrderListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/pages/mypage/orderHistory.jsp";
		
//		로그인 여부
//	    HttpSession session = request.getSession();
//	    MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");

//	    if (loginUser == null) {
//	        url = "/pages/login_form.jsp";
//	      } 
//	    else {
//			
//	    }
		
		
		String pno = request.getParameter("pno");
		OrderDAO orderDAO = OrderDAO.getInstance();
		
		int totalCount = orderDAO.totalCountOrder(); //전체 주문내역 수	
		int pageNumber = 1; // 현재 페이지 번호
		int pagePerList = 5; // 보여줄 페이지 수
		int listPerPage = 2; // 한 페이지 당 보여줄 주문내역 수


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
		
		
	    ArrayList<OrderDTO> orderList = orderDAO.listOrder(beginRow, endRow, 6);
	    System.out.println("-----------------");
	    System.out.println("totalOrderCnt: " + totalCount);
	    System.out.println("slice size: " + orderList.size());
	    
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
	    
	    request.setAttribute("orderList", orderList);
	    request.getRequestDispatcher(url).forward(request, response);
	}
}
