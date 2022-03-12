package com.heendy.action.order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.heendy.action.Action;
import com.heendy.common.ErrorCode;
import com.heendy.common.ErrorResponse;
import com.heendy.common.SQLErrorCode;
import com.heendy.common.exception.MemberNotExistSession;
import com.heendy.dao.OrderDAO;
import com.heendy.dto.MemberDTO;
import com.heendy.dto.order.CreateCartOrderDTO;
import com.heendy.utils.SessionUserService;
import com.heendy.utils.UserService;

/**
 * @author 이승준
 * 
 *         장바구니에 담긴 상품중 선택한 물품 모두 결제 Action 클래스
 */
public class OrderCartProductsAction implements Action {

	private final OrderDAO orderDAO = OrderDAO.getInstance();

	private UserService<MemberDTO, HttpSession> userService = SessionUserService.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ErrorResponse errorResponse;

		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");

			MemberDTO member = this.userService.loadUser(request.getSession()).orElseThrow(MemberNotExistSession::new);

			String[] t = request.getParameterValues("itemSelect");
			System.out.println(t.length);
			System.out.println(t[0]);

			Integer[] cartIds = Arrays.stream(request.getParameterValues("itemSelect")).mapToInt(Integer::parseInt)
					.boxed().toArray(Integer[]::new);

			CreateCartOrderDTO createCartOrderDTO = new CreateCartOrderDTO(member.getMemberId(), cartIds);

			orderDAO.createCartOrder(createCartOrderDTO);

			response.setStatus(201);
			response.getWriter().write("{\"created\" : true, \"result\" :\"주문이 완료 되었습니다.\"}");
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();

			if (errorCode == SQLErrorCode.NO_DATA_FOUND.getCode()) {
				errorResponse = ErrorResponse.of(ErrorCode.NO_DATA_FOUND);
			} else if (errorCode == SQLErrorCode.NOT_RESOURCE_OWNER.getCode()) {
				errorResponse = ErrorResponse.of(ErrorCode.NOT_RESOURCE_OWNER);
			} else if (errorCode == SQLErrorCode.LACK_OF_STOCK.getCode()) {
				errorResponse = ErrorResponse.of(ErrorCode.LACK_OF_STOCK);
			} else {
				System.out.println(e.getMessage());
				errorResponse = ErrorResponse.of(ErrorCode.UNCAUGHT_SERVER_ERROR);
			}

			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);
		}

	}

}
