package com.heendy.action.order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.heendy.action.Action;
import com.heendy.common.ErrorCode;
import com.heendy.common.ErrorResponse;
import com.heendy.common.ErrorResponse.ErrorField;
import com.heendy.common.SQLErrorCode;
import com.heendy.common.ValidableAction;
import com.heendy.common.exception.MemberNotExistSession;
import com.heendy.dao.OrderDAO;
import com.heendy.dto.MemberDTO;
import com.heendy.dto.order.CreateOrderDTO;
import com.heendy.utils.SessionUserService;
import com.heendy.utils.UserService;
import com.heendy.utils.Validation;

public class OrderProductAction implements Action, ValidableAction {

	private final OrderDAO orderDAO = OrderDAO.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");

			int memberId = (int) request.getAttribute("memberId");

			List<ErrorResponse.ErrorField> errors = valid(request);

			if (errors.size() != 0) {
				ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_FIELDS);

				errorResponse.setErrors(errors);

				String json = new Gson().toJson(errorResponse);
				response.setStatus(errorResponse.getStatus());
				response.getWriter().write(json);
				return;
			}

			int proudctId = Integer.parseInt(request.getParameter("product_id"));
			int companyId = Integer.parseInt(request.getParameter("company_id"));
			int count = Integer.parseInt(request.getParameter("product_count"));

			CreateOrderDTO createOrderDTO = new CreateOrderDTO(proudctId, companyId, memberId, count);

			orderDAO.createOrder(createOrderDTO);

			response.setStatus(201);
			response.getWriter().write("{\"created\" : true, \"result\" : \"주문이 완료 되었습니다.\"}");

		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			ErrorResponse errorResponse;
			if (errorCode == SQLErrorCode.NO_DATA_FOUND.getCode()) {
				errorResponse = ErrorResponse.of(ErrorCode.NO_DATA_FOUND);
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

	@Override
	public List<ErrorField> valid(HttpServletRequest request) {
		List<ErrorResponse.ErrorField> errors = new ArrayList<>();

		String productId = request.getParameter("product_id");
		String compayId = request.getParameter("company_id");
		String count = request.getParameter("product_count");

		Validation validation = Validation.getInstance();

		if (!validation.validNotEmpty(productId)) {
			errors.add(new ErrorResponse.ErrorField("product_id", productId, "값이 비어있습니다."));
		}

		if (!validation.validNotEmpty(compayId)) {
			errors.add(new ErrorResponse.ErrorField("company_id", compayId, "값이 비어있습니다."));
		}

		if (!validation.validNotEmpty(count)) {
			errors.add(new ErrorResponse.ErrorField("product_count", count, "값이 비어있습니다."));
		}

		return errors;
	}

}
