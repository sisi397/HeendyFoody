package com.heendy.action.mypage;

import java.io.IOException;
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


public class RecentViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/pages/mypage/recentView.jsp"; 
		
		
		CookieUtils ck = new CookieUtils();
		List<String> rvItems = ck.getValueList("RECENT_VIEW_ITEMS", request);
		
		//Integer[]로 변환 작업
		String[] rvStrArray = rvItems.toArray(new String[rvItems.size()]);
		int [] rvIntArray = Arrays.stream(rvStrArray).mapToInt(Integer::parseInt).toArray();
		Integer[] rvProductsAll = Arrays.stream(rvIntArray).boxed().toArray(Integer[]::new);
		
		//뽑아오고 싶은 개수
		int rv_cnt = 20;
		if (rvProductsAll.length < 20) {
			rv_cnt = rvProductsAll.length;
		}
		//자를 위치
		int slice_position = rvProductsAll.length - rv_cnt;
		
		//배열 자르기
		Integer[] rvProducts1 = Arrays.copyOfRange(rvProductsAll, slice_position, rvProductsAll.length);
		
		//배열 뒤집기
		Integer[] rvProducts2 = new Integer[rvProducts1.length];
		
		for (int i = rvProducts1.length-1, j = 0; i >= 0; i--, j++) {
			rvProducts2[j] = rvProducts1[i];
		}
			
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("rvProducts: " + rvProducts2);
		
		RecentViewDAO recentViewDAO = RecentViewDAO.getInstance();
		ArrayList<RecentViewDTO> rvList = recentViewDAO.listRecentView(rvProducts2);
		
	    request.setAttribute("rvList", rvList);
		
		request.getRequestDispatcher(url).forward(request, response);

	}

}
