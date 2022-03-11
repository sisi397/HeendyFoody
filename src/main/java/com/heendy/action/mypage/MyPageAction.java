package com.heendy.action.mypage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.heendy.action.Action;
import com.heendy.dao.RecentViewDAO;
//import com.heendy.dto.MemberDTO;
import com.heendy.dao.WishDAO;
import com.heendy.dto.RecentViewDTO;
import com.heendy.dto.WishDTO;
import com.heendy.utils.CookieUtils;

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
		int totalWishCount = wishDAO.totalWishCount(6);
		
		int beginRow = 1;
		int endRow = 5;
		if(endRow > totalWishCount) {
			endRow = totalWishCount;
		}
		
		ArrayList<WishDTO> wishList = wishDAO.listWish(beginRow, endRow, 6);
		
	    request.setAttribute("totalWishCount", totalWishCount);
	    request.setAttribute("wishList", wishList);
	    
	    
	    CookieUtils ck = new CookieUtils();
		List<String> rvItems = ck.getValueList("RECENT_VIEW_ITEMS", request);
		
		//Integer[]�� ��ȯ �۾�
		if (rvItems != null) {
			String[] rvStrArray = rvItems.toArray(new String[rvItems.size()]);	
			
			int [] rvIntArray = Arrays.stream(rvStrArray).mapToInt(Integer::parseInt).toArray();
			Integer[] rvProductsAll = Arrays.stream(rvIntArray).boxed().toArray(Integer[]::new);
			
			int rv_cnt = 5;
			if (rvProductsAll.length < 5) {
				rv_cnt = rvProductsAll.length;
			}
			
			int slice_position = rvProductsAll.length - rv_cnt;
			
			Integer[] rvProducts1 = Arrays.copyOfRange(rvProductsAll, slice_position, rvProductsAll.length);
			Integer[] rvProducts2 = new Integer[rvProducts1.length];
			System.out.println(rvProducts1.length);
			
			for (int i = rvProducts1.length-1, j = 0; i >= 0; i--, j++) {
				rvProducts2[j] = rvProducts1[i];
			}
			
			RecentViewDAO recentViewDAO = RecentViewDAO.getInstance();
			ArrayList<RecentViewDTO> rvList = recentViewDAO.listRecentView(rvProducts2);
			
			
			int totalRvCount = rvProductsAll.length;
			
			request.setAttribute("rvList", rvList);
			request.setAttribute("totalRvCount", totalRvCount);
			
			
		} else {
			
			ArrayList<RecentViewDTO> rvList = new ArrayList<>();
			int totalRvCount = 0;
			
			request.setAttribute("rvList", rvList);
			request.setAttribute("totalRvCount", totalRvCount );
			
		}
		
		
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
