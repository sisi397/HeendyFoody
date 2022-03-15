package com.heendy.action.mypage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.dao.RecentViewDAO;
import com.heendy.dto.RecentViewDTO;
import com.heendy.utils.CookieUtils;

/**
 * @author : 이지민
 * 최근 본 상품 목록 조회 Action 클래스
 * */

public class RecentViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//dispatch할 페이지 주소
		String url = "/pages/mypage/recentView.jsp"; 
		
		try {
			
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
				int rv_cnt = 20;
				if (rvProductsAll.length < 20) {
					rv_cnt = rvProductsAll.length;
				}
				
				//최근 본 순서대로 정렬하기 위한 작업
				int slice_position = rvProductsAll.length - rv_cnt;
				
				Integer[] rvProducts1 = Arrays.copyOfRange(rvProductsAll, slice_position, rvProductsAll.length);
				Integer[] rvProducts2 = new Integer[rvProducts1.length];
				
				for (int i = rvProducts1.length-1, j = 0; i >= 0; i--, j++) {
					rvProducts2[j] = rvProducts1[i];
				}
				
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("rvProducts: " + rvProducts2);
				
				//인스턴스 생성 및 최근 본 상품 목록 조회하기
				RecentViewDAO recentViewDAO = RecentViewDAO.getInstance();
				ArrayList<RecentViewDTO> rvList = recentViewDAO.listRecentView(rvProducts2);
				
				request.setAttribute("rvList", rvList);
			
				
			//최근 본 상품 목록이 없다면 빈 리스트 넘겨주기
			} else {				
				ArrayList<RecentViewDTO> rvList = new ArrayList<>();	
				request.setAttribute("rvList", rvList);
			}
						
		} catch (SQLException e) {
			request.setAttribute("errorMsg", e.getMessage());
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
