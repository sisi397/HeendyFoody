package com.heendy.action.mypage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.heendy.action.Action;
import com.heendy.dao.MemberDAO;
import com.heendy.dao.OrderDAO;
import com.heendy.dao.RecentViewDAO;
import com.heendy.dto.MemberDTO;
import com.heendy.dao.WishDAO;
import com.heendy.dto.RecentViewDTO;
import com.heendy.dto.WishDTO;
import com.heendy.utils.CookieUtils;

/**
 * @author : 이지민
 * 마이페이지 관련 정보 조회 Action 클래스
 * */

public class MyPageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//dispatch할 페이지 주소
		String url = "/pages/mypage/mypage.jsp"; 
		
		try {
			
			//세션에서 로그인 정보 받아와 request에 담기
			MemberDTO member = (MemberDTO) request.getAttribute("loginUser");	
			
			//사용자 아이디 가져오기
			int memberId = member.getMemberId();
			
			
			//사용자 포인트 가져오기
			MemberDAO memberDAO = MemberDAO.getInstance();
			int memberPoint = memberDAO.getMemberPoint(memberId);
			request.setAttribute("memberPoint", memberPoint);	
			
			//인스턴스 생성 및 총 좋아요 수 구하기
			WishDAO wishDAO = WishDAO.getInstance();
			int totalWishCount = wishDAO.totalWishCount(memberId);
			
			//총 좋아요 수에 따라 분기처리
			if (totalWishCount == 0) {
				ArrayList<WishDTO> wishList = new ArrayList<>();
				request.setAttribute("totalWishCount", totalWishCount);
				request.setAttribute("wishList", wishList);
				
			} else {
				
				// DB에서 좋아요 목록 가져올 범위 지정(5개)
				int endRow = totalWishCount;
				int beginRow = endRow - 4;
				if (beginRow < 1) {
					beginRow = 1;
				}
				
				//좋아요 목록 조회
				ArrayList<WishDTO> wishList = wishDAO.listWish(beginRow, endRow, memberId);
				
				request.setAttribute("totalWishCount", totalWishCount);
				request.setAttribute("wishList", wishList);
			}
			
			
			//인스턴스 생성 및 총 주문내역 수 구하기
			OrderDAO orderDAO = OrderDAO.getInstance();
			int totalOrderCount = orderDAO.totalCountOrder(memberId);
			request.setAttribute("totalOrderCount", totalOrderCount);
			
			
			//최근 본 상품 목록 쿠키 가져와서 list에 담기
			CookieUtils ck = new CookieUtils();
			List<String> rvItems = ck.getValueList("RECENT_VIEW_ITEMS", request);
			
			//최근 본 상품 목록 존재 유무에 따라 분기처리
			if (rvItems != null) {
				
				//List<String> --> Integer[]
				String[] rvStrArray = rvItems.toArray(new String[rvItems.size()]);	
				int [] rvIntArray = Arrays.stream(rvStrArray).mapToInt(Integer::parseInt).toArray();
				Integer[] rvProductsAll = Arrays.stream(rvIntArray).boxed().toArray(Integer[]::new);
				
				//페이지에서 보여 줄 상품 개수
				int rv_cnt = 5;
				if (rvProductsAll.length < 5) {
					rv_cnt = rvProductsAll.length;
				}
				
				//최근 본 순서대로 정렬하기 위한 작업
				int slice_position = rvProductsAll.length - rv_cnt;
				
				Integer[] rvProducts1 = Arrays.copyOfRange(rvProductsAll, slice_position, rvProductsAll.length);
				Integer[] rvProducts2 = new Integer[rvProducts1.length];
				System.out.println(rvProducts1.length);
				
				for (int i = rvProducts1.length-1, j = 0; i >= 0; i--, j++) {
					rvProducts2[j] = rvProducts1[i];
				}
				
				//인스턴스 생성 및 최근 본 상품 목록 조회하기
				RecentViewDAO recentViewDAO = RecentViewDAO.getInstance();
				ArrayList<RecentViewDTO> rvList = recentViewDAO.listRecentView(rvProducts2);
				
				//최근 본 상품 목록 총 개수 구하기 
				int totalRvCount = rvProductsAll.length;
				
				request.setAttribute("rvList", rvList);
				request.setAttribute("totalRvCount", totalRvCount);
				
				
			//최근 본 상품 목록이 없다면 빈 리스트 넘겨주기	
			} else {				
				ArrayList<RecentViewDTO> rvList = new ArrayList<>();
				int totalRvCount = 0;
				
				request.setAttribute("rvList", rvList);
				request.setAttribute("totalRvCount", totalRvCount );
				
			}
			
		} catch (SQLException e) {
			request.setAttribute("errorMsg", e.getMessage());
		}
	
		request.getRequestDispatcher(url).forward(request, response);
	}

}
