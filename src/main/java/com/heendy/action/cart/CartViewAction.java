package com.heendy.action.cart;

import java.io.IOException;
import java.sql.SQLException;
import java.text.Collator;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.heendy.action.Action;
import com.heendy.common.exception.MemberNotExistSession;
import com.heendy.dao.CartDAO;
import com.heendy.dto.MemberDTO;
import com.heendy.dto.cart.CartItemDTO;
import com.heendy.utils.SessionUserService;
import com.heendy.utils.UserService;

public class CartViewAction implements Action {

	private final CartDAO cartDAO = CartDAO.getInstance();

	private final String VIEW_URL = "/pages/cart/shoppingCartList.jsp";
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

		
			MemberDTO member = (MemberDTO) request.getAttribute("member");

			List<CartItemDTO> cartList = cartDAO.getCartList(member.getMemberId());
			
			/*일반 장바구니 리스트*/
			List<CartItemDTO> normalCartList = cartList
					.stream()
					.filter(item -> item.getProductCount() != 0 && !item.isDeleted())
					.collect(Collectors.toList());
			/*품절(상품 재고가 0이거나 삭제된 상품)된 상품 장바구니 리스트*/
			List<CartItemDTO> soldOutCartList = cartList
					.stream()
					.filter(item -> item.getProductCount() == 0 || item.isDeleted())
					.collect(Collectors.toList());
			

			request.setAttribute("normalCartList", normalCartList);
			request.setAttribute("soldOutCartList", soldOutCartList);

		} catch (SQLException e) {
			request.setAttribute("errorMsg", e.getMessage());
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_URL);
		dispatcher.forward(request, response);

	}

}
