package com.heendy.action.mypage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.heendy.action.Action;
import com.heendy.dto.MemberDTO;
import com.heendy.dto.OrderDTO;
import com.heendy.dao.OrderDAO;

/**
 * @author : 이지민
 * 주문내역 조회 및 페이지네이션 처리 Action 클래스
 * */

public class OrderListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//dispatch할 페이지 주소
		String url = "/pages/mypage/orderHistory.jsp";
		
		try {
						
			MemberDTO member = (MemberDTO) request.getAttribute("loginUser");	
			
			//사용자 아이디 가져오기
			int memberId = member.getMemberId();
						
			//인스턴스 생성 및 총 주문내역 수 구하기
			OrderDAO orderDAO = OrderDAO.getInstance();
			int totalCount = orderDAO.totalCountOrder(memberId);	
			
			//총 주문내역 수에 따라 분기처리
			if (totalCount == 0) {
				ArrayList<OrderDTO> orderList = new ArrayList<>();
				request.setAttribute("orderList", orderList);
				
			} else {
				
				//현재 페이지 번호 받아오기
				String pno = request.getParameter("pno");
				
				
				int pageNumber = 1; // 현재 페이지 번호
				int pagePerList = 5; // 한 화면에 보여줄 페이지 수
				int listPerPage = 10; // 한 페이지에 보여줄 주문내역 수
				
				
				// pageNumber 세팅
				if (pno == null || pno.length() == 0) {
					pageNumber = 1;
				}
				try {
					pageNumber = Integer.parseInt(pno);
				} 
				catch (NumberFormatException e) {
					pageNumber = 1;
				}
				
				// DB에서 주문내역 가져올 범위 지정
				int beginRow = (pageNumber - 1) * listPerPage + 1;
				int endRow = beginRow + listPerPage - 1;
				if (endRow > totalCount) {
					endRow = totalCount;
				}
				
				//주문내역 목록 조회
				ArrayList<OrderDTO> orderList = orderDAO.listOrder(beginRow, endRow, memberId);
				System.out.println("-----------------");
				System.out.println("totalOrderCnt: " + totalCount);
				System.out.println("slice size: " + orderList.size());
				
				//request에 담아 넘겨 줄 값 세팅
				int beginPageNumber = (pageNumber - 1) / pagePerList * pagePerList + 1; //시작페이지 번호
				int endPageNumber = beginPageNumber + pagePerList - 1; //끝페이지 번호
				int totalPage = (totalCount - 1) / listPerPage + 1; //총 페이지 수
				
				if (totalPage < endPageNumber) {
					endPageNumber = totalPage;
				}	    
				System.out.println("totalpage : " + totalPage);
				
				
				request.setAttribute("beginPage", beginPageNumber);
				request.setAttribute("endPage", endPageNumber);
				request.setAttribute("pagePerList", pagePerList);
				request.setAttribute("totalPageCount", totalPage);
				
				request.setAttribute("orderList", orderList);
				
			}
			
		} catch (SQLException e) {
			request.setAttribute("errorMsg", e.getMessage());
		}
		
    	request.getRequestDispatcher(url).forward(request, response);  
	}
}
