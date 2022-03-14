package com.heendy.action.mypage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.heendy.action.Action;
import com.heendy.dao.WishDAO;
import com.heendy.dto.MemberDTO;
import com.heendy.dto.WishDTO;

public class WishAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/pages/mypage/wish.jsp";
		
		HttpSession session = request.getSession();
		MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
		
		request.setAttribute("loginUser", loginUser);
		
		int memberId = loginUser.getMemberId();
		    
		
		//�α��� ���� �б�ó�� �ʿ�
		
		String pno = request.getParameter("pno");
		WishDAO wishDAO = WishDAO.getInstance();
		
		int totalCount = wishDAO.totalWishCount(memberId); //��ü ���ƿ� ��ǰ ��
		System.out.println("totalCount: " + totalCount);
		
		if (totalCount == 0) {
			 ArrayList<WishDTO> wishList = new ArrayList<>();
			 request.setAttribute("wishList", wishList);
			 request.getRequestDispatcher(url).forward(request, response);
		
		} else {
			
			int pageNumber = 1; // ���� ������ ��ȣ
			int pagePerList = 5; // ������ ������ ��
			int listPerPage = 10; // �� ������ �� ������ ���ƿ� ��ǰ ��
			
			
			// ������ ��ȣ ���
			if (pno == null || pno.length() == 0) {
				pageNumber = 1;
			}
			try {
				pageNumber = Integer.parseInt(pno);
			} 
			catch(NumberFormatException e) {
				pageNumber = 1;
			}
			
    		int endRow = totalCount - (listPerPage * (pageNumber-1));
    		int beginRow = endRow - listPerPage + 1;
    		if (beginRow < 1) {
    			beginRow = 1;
    		}
			
			
			ArrayList<WishDTO> wishList = wishDAO.listWish(beginRow, endRow, memberId);
			
			int beginPageNumber = (pageNumber - 1) / pagePerList * pagePerList + 1;
			int endPageNumber = beginPageNumber + pagePerList - 1;
			int totalPage = (totalCount - 1) / listPerPage + 1; // �� ������ ��
			System.out.println("totalpage : " + totalPage);
			if (totalPage < endPageNumber) {
				endPageNumber = totalPage;
			}	    
			
//			JsonObject jsonObj = new JsonObject();
//			JsonArray data = new JsonArray();
//			
//			for (int i = 0; i < wishList.size(); i++) {
//				JsonObject obj = new JsonObject();
//				obj.addProperty("memberId", wishList.get(i).getMemberId());
//				obj.addProperty("companyId", wishList.get(i).getCompanyId());
//				obj.addProperty("productId", wishList.get(i).getProductId());
//				obj.addProperty("productName", wishList.get(i).getProductName());
//				obj.addProperty("imageUrl", wishList.get(i).getImageUrl());
//				obj.addProperty("productPrice", wishList.get(i).getProductPrice());
//				obj.addProperty("discountPrice", wishList.get(i).getDiscountPrice());
//				obj.addProperty("productCount", wishList.get(i).getProductCount());
//				data.add(obj);
//			}
//			jsonObj.add("data", data);
//	
//			jsonObj.addProperty("beginPage", beginPageNumber);
//			jsonObj.addProperty("endPage", endPageNumber);
//			jsonObj.addProperty("pagePerList", pagePerList);
//			jsonObj.addProperty("totalPageCount", totalPage);
			
//			jsonObj.add("wishList", wishList);
//			String json = new Gson().toJson(jsonObj);
//			System.out.println(json);
//			PrintWriter out = response.getWriter();
//			out.print(json.toString());
//			response.getWriter().write(json);
			request.setAttribute("beginPage", beginPageNumber);
			request.setAttribute("endPage", endPageNumber);
			request.setAttribute("pagePerList", pagePerList);
			request.setAttribute("totalPageCount", totalPage);
			
			request.setAttribute("wishList", wishList);
		}
		
		
	    request.getRequestDispatcher(url).forward(request, response);	

	}

}
