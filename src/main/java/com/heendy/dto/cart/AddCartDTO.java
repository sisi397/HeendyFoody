package com.heendy.dto.cart;

public class AddCartDTO {
	
	private int cartId;
	
	private int count;
	

	public AddCartDTO(int cartId, int count) {
		this.cartId = cartId;
		this.count = count;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	

}
