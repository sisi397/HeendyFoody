package com.heendy.dto.cart;


/**
 * @author 이승준
 * 장바구니 아이템 DTO 클래스
 * */
public class CartItemDTO {

	private int cartId;
	
	private int productId;
	
	private int companyId;
	
	private int cartCount;
	
	private int productCount;
	
	private String productName;
	
	private String imgUrl;
	
	private int productPrice;
	
	private int productDisCountedPrice;
	
	private boolean isDeleted;

	public CartItemDTO(int cartId, int productId, int companyId, int cartCount, int productCount, String productName, String imgUrl, int productPrice,
			int productDisCountedPrice, boolean isDeleted) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.companyId = companyId;
		this.cartCount = cartCount;
		this.productCount = productCount;
		this.productName = productName;
		this.imgUrl = imgUrl;
		this.productPrice = productPrice;
		this.productDisCountedPrice = productDisCountedPrice;
		this.isDeleted = isDeleted;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	
	public int getCartCount() {
		return cartCount;
	}

	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductDisCountedPrice() {
		return productDisCountedPrice;
	}

	public void setProductDisCountedPrice(int productDisCountedPrice) {
		this.productDisCountedPrice = productDisCountedPrice;
	}
	
	

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	
	
	
	
}
