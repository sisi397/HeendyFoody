package com.heendy.action.mypage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.heendy.action.Action;
import com.heendy.dao.WishDAO;
import com.heendy.dto.MemberDTO;
import com.heendy.dto.WishDTO;

/**
 * @author : 이지민
 * 좋아요 조회 및 페이지네이션 처리 Action 클래스
 * */

public class WishAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//dispatch할 페이지 주소
		String url = "/pages/mypage/wish.jsp";
		
		try {
						
			MemberDTO member = (MemberDTO) request.getAttribute("loginUser");	
			//사용자 아이디 가져오기
			int memberId = member.getMemberId();
			
			//인스턴스 생성 및 총 좋아요 수 구하기
			WishDAO wishDAO = WishDAO.getInstance();
			int totalCount = wishDAO.totalWishCount(memberId); 
			System.out.println("totalCount: " + totalCount);
							
			//총 좋아요 수에 따라 분기처리
			if (totalCount == 0) {
				ArrayList<WishDTO> wishList = new ArrayList<>();
				request.setAttribute("wishList", wishList);
				
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
				catch(NumberFormatException e) {
					pageNumber = 1;
				}
				
				// DB에서 좋아요 가져올 범위 지정(최신순으로 시도했으나 실패--> DB에 좋아요한 순서대로 들어가 쌓이는 줄 알았는데 그게 아니었다..)
				//created at 컬럼을 기존 테이블에 추가로 만들어야 가능할 것 같다  
				int endRow = totalCount - (listPerPage * (pageNumber-1));
				int beginRow = endRow - listPerPage + 1;
				if (beginRow < 1) {
					beginRow = 1;
				}
				
				//좋아요 목록 조회
				ArrayList<WishDTO> wishList = wishDAO.listWish(beginRow, endRow, memberId);
				
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
				
				request.setAttribute("wishList", wishList);
			}
			
		} catch (SQLException e) {
			request.setAttribute("errorMsg", e.getMessage());
		}
		
	    request.getRequestDispatcher(url).forward(request, response);
	}

}
