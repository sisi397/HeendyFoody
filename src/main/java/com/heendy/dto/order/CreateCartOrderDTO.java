package com.heendy.dto.order;

import java.util.List;

/**
 * @author 이승준
 * 장바구니에 담긴 상품 전체 결제 DTO 클래스
 * */
public class CreateCartOrderDTO {
	
	private int memberId;
	
	private Integer[] cartIds;
	
	

	public CreateCartOrderDTO(int memberId, Integer[] cartIds) {
		super();
		this.memberId = memberId;
		this.cartIds = cartIds;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public Integer[] getCartIds() {
		return cartIds;
	}

	public void setCartIds(Integer[] cartIds) {
		this.cartIds = cartIds;
	}	
	
}
