package com.heendy.dto.cart;

/**
 *  @author 이승준
 *  
 *  장바구니 수량 증가 및 감소를 위한 DTO 클래스
 * */
public class CartCountUpdateDTO {
	
	private int cartId;
	
	private int memberId;
	
	private int count;
	

	public CartCountUpdateDTO(int cartId, int memberId, int count) {
		this.cartId = cartId;
		this.memberId = memberId;
		this.count = count;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	

}
