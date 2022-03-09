package com.heendy.action.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.heendy.action.Action;
import com.heendy.dao.ProductDAO;
import com.heendy.dto.ProductDTO;

public class ProductListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pno = request.getParameter("pno");
		String sort = request.getParameter("sort");
		ProductDAO productDAO = ProductDAO.getInstance();
		int totalCount = productDAO.totalCountProduct();// 전체 상품 목록 수
		
		int pageNumber = 1; // 현재 페이지 번호
		int pagePerList = 5; // 보여줄 페이지 수
		int listPerPage = 20; // 한 페이지 당 보여줄 게시글 수
		int totalPage = totalCount % listPerPage == 1 ? totalCount/listPerPage : totalCount/listPerPage + 1; //전체 페이지 수

		// 페이지 번호 계산
		if(pno != null && pno.length() > 0)
			pageNumber = Integer.parseInt(pno);
		
		// 정렬
		if(sort == null || sort.length() == 0)
			sort = "product_reg_date desc";
		else if(sort.equals("A"))
			sort = "product_reg_date desc"; //신상품순
		else if(sort.equals("B"))
			sort = "discount_price"; //인기상품 순
		else if(sort.equals("C"))
			sort = "discount_price"; //낮은가격 순
		else if(sort.equals("D"))
			sort = "discount_price desc"; //높은가격 순
			
		
		int beginRow = (pageNumber - 1) * listPerPage + 1;
		int endRow = beginRow + listPerPage - 1;
		if(endRow > totalCount)
			endRow = totalCount;
		
		// 상품 LIST 가져오기
		// dao에서
		ArrayList<ProductDTO> productList = productDAO.listProduct(beginRow, endRow, sort);
		
		int beginPageNumber = (pageNumber - 1)/pagePerList*pagePerList + 1;
		int endPageNumber = beginPageNumber + pagePerList - 1;
		if(endPageNumber > totalPage)
			endPageNumber = totalPage;
		
		request.setAttribute("productList", productList);
		request.setAttribute("beginPage", beginPageNumber);
		request.setAttribute("endPage", endPageNumber);
		request.setAttribute("pagePerList", pagePerList);
		request.setAttribute("totalPageCount", totalPage);
	    
//		PrintWriter writer = response.getWriter();
//		
//		JSONObject totalObject = new JSONObject();
//		JSONArray productArray = new JSONArray();
//		JSONObject productInfo;
//		for(ProductDTO product : productList) {
//			productInfo = new JSONObject();
//			productInfo.put("productName", product.getProductName());
//			productInfo.put("productId", product.getProductId());
//			productArray.add(productInfo);
//		}
//		totalObject.put("productList",productArray);
//		
//		String jsonInfo = totalObject.toJSONString();
//		System.out.println(jsonInfo);
//		writer.print(jsonInfo);
//		
//		JSONObject addObject = new JSONObject();
//		addObject.put("beginPage", beginPageNumber);
//		writer.print(addObject);
//		System.out.println(addObject);
		
		String url = "/pages/product/productList.jsp";
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}

}
